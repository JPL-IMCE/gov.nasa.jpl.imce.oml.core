/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */
package gov.nasa.jpl.imce.oml.dsl.scoping

import com.google.common.base.Function
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.ArrayList
import gov.nasa.jpl.imce.oml.model.common.Annotation
import gov.nasa.jpl.imce.oml.model.terminologies.AspectSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRelationship
import gov.nasa.jpl.imce.oml.model.common.Resource
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom
import gov.nasa.jpl.imce.oml.model.common.Element
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.SimpleScope
import gov.nasa.jpl.imce.oml.model.extensions.OMLExtensions

class OMLScopeExtensions {
	
	@Inject extension OMLExtensions
	
	@Inject IQualifiedNameConverter qnc
	
	/*
	 * The syntax of Annotation involves "@<annotation property abbrev IRI> = <annotation value>".
	 * Therefore, construct the resolvable scope of AnnotationProperties
	 * in terms of the abbrevIRI of each AnnotationProperty in the TerminologyExtent.
	 */
	def scope_Annotation_property(Annotation annotation, EReference eRef) {
		Scopes.scopeFor(
			annotation.module.extent.annotationProperties,
			[ qnc.toQualifiedName(it.abbrevIRI) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_AspectSpecializationAxiom_subEntity(AspectSpecializationAxiom context) {
		context.getTbox.allEntitiesScope
	}
	
	def scope_AspectSpecializationAxiom_superAspect(AspectSpecializationAxiom context) {
		context.getTbox.allAspectsScope
	}
	
	def scope_BundledTerminologyAxiom_bundledTerminology(BundledTerminologyAxiom context) {
		Scopes.scopeFor(
			context.bundle.extent.terminologies,
			[ qnc.toQualifiedName(it.nsPrefix) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_ConceptDesignationTerminologyAxiom_designatedTerminology(ConceptDesignationTerminologyAxiom context) {
		Scopes.scopeFor(
			context.getTbox.extent.terminologies,
			[ qnc.toQualifiedName(it.nsPrefix) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_ConceptDesignationTerminologyAxiom_designatedConcept(ConceptDesignationTerminologyAxiom context) {
		context.designatedTerminology.allConceptsScope
	}
	
	def scope_EntityRelationship(EntityRelationship context) {
		context.getTbox.allEntitiesScope
	}
	
	
	/*
	 * The syntax of TerminologyExtensionAxioms involves "extends <extended terminology prefix>".
	 * Therefore, construct the resolvable scope of TerminologyBoxes
	 * in terms of the nsPrefix of each TerminologyBox in the TerminologyExtent.
	 */
	def scope_TerminologyExtensionAxiom_extendedTerminology(TerminologyExtensionAxiom context, EReference eRef) {
		val ext = context.getTbox.extent
		val tboxes = ext.terminologyGraphs + ext.bundles
		Scopes.scopeFor(
			tboxes,
			[qnc.toQualifiedName(it.nsPrefix) ], 
			IScope.NULLSCOPE)
	}
	
	// =================================================
	
	/*
	 * Computes an IScope for 
	 */
	def <T extends Element> IScope terminologyScope(
		TerminologyBox tbox,
		Function<TerminologyBox, Iterable<T>> localScopeFunction,
		Function<Pair<TerminologyBox, T>, QualifiedName> nameFunction
	) {
		val ArrayList<IEObjectDescription> result = Lists.newArrayList()
		result.addAll(Scopes.scopedElementsFor(localScopeFunction.apply(tbox)))
		result.addAll(tbox.allImportedTerminologies.map[importedTbox|
			Scopes.scopedElementsFor(
				localScopeFunction.apply(importedTbox), 
				[importedThing| nameFunction.apply(Pair.of(importedTbox, importedThing)) ]
			)
		].flatten)
		new SimpleScope(result)
	}
	
	def <T extends Resource> QualifiedName importedResourceNameFunction(Pair<TerminologyBox, T> p) {
		qnc.toQualifiedName(p.key.nsPrefix + ":" + p.value.name())
	}
	
	def IScope allEntitiesScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localEntities], [importedResourceNameFunction])
	}
	
	def IScope allAspectsScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localAspects], [importedResourceNameFunction])
	}
	
	def IScope allConceptsScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localConcepts], [importedResourceNameFunction])
	}
	
	def IScope allReifiedRelationshipsScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localReifiedRelationships], [importedResourceNameFunction])
	}
	
}