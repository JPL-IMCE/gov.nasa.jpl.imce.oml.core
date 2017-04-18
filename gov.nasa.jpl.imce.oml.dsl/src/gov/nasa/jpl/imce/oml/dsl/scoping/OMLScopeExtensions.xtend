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
import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.common.Annotation
import gov.nasa.jpl.imce.oml.model.common.Element
import gov.nasa.jpl.imce.oml.model.common.Resource
import gov.nasa.jpl.imce.oml.model.extensions.OMLExtensions
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom
import java.util.ArrayList
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.SimpleScope
import gov.nasa.jpl.imce.oml.model.bundles.Bundle
import gov.nasa.jpl.imce.oml.model.common.Extent

class OMLScopeExtensions {
	
	@Inject extension OMLExtensions
	
	@Inject IQualifiedNameConverter qnc
	
	/*
	 * The syntax of Annotation involves "@<annotation property abbrev IRI> = <annotation value>".
	 * Therefore, construct the resolvable scope of AnnotationProperties
	 * in terms of the abbrevIRI of each AnnotationProperty in the TerminologyExtent.
	 */
	def scope_Annotation_property(Annotation annotation, EReference eRef) {
		val exts = annotation.eResource.resourceSet.resources.map[contents.filter(Extent)].flatten
		val annoationProperties = exts.map[annotationProperties].flatten
		Scopes.scopeFor(
			annoationProperties,
			[ qnc.toQualifiedName(it.abbrevIRI) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_BundledTerminologyAxiom_bundledTerminology(BundledTerminologyAxiom context) {
		val exts = context.eResource.resourceSet.resources.map[contents.filter(Extent)].flatten
		val tboxes = exts.map[terminologyGraphs + bundles].flatten
		Scopes.scopeFor(
			tboxes,
			[ qnc.toQualifiedName(it.nsPrefix) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_ConceptDesignationTerminologyAxiom_designatedTerminology(ConceptDesignationTerminologyAxiom context) {
		val exts = context.eResource.resourceSet.resources.map[contents.filter(Extent)].flatten
		val tboxes = exts.map[terminologyGraphs + bundles].flatten
		Scopes.scopeFor(
			tboxes,
			[ qnc.toQualifiedName(it.nsPrefix) ],
			IScope.NULLSCOPE)	
	}
	
	def scope_ConceptDesignationTerminologyAxiom_designatedConcept(ConceptDesignationTerminologyAxiom context) {
		context.designatedTerminology.allConceptsScope
	}
	
	/*
	 * The syntax of TerminologyExtensionAxioms involves "extends <extended terminology prefix>".
	 * Therefore, construct the resolvable scope of TerminologyBoxes
	 * in terms of the nsPrefix of each TerminologyBox in the TerminologyExtent.
	 */
	def scope_TerminologyExtensionAxiom_extendedTerminology(TerminologyExtensionAxiom context, EReference eRef) {
		val exts = context.eResource.resourceSet.resources.map[contents.filter(Extent)].flatten
		val tboxes = exts.map[terminologyGraphs + bundles].flatten
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
	
	def <T extends Resource> QualifiedName importedBundleNameFunction(Pair<Bundle, T> p) {
		qnc.toQualifiedName(p.key.nsPrefix + ":" + p.value.name())
	}
	
	def IScope allEntitiesScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localEntities], [importedResourceNameFunction])
	}
	
	def IScope allRangesScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localRanges], [importedResourceNameFunction])
	}
	
	def IScope allStructuresScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localStructures], [importedResourceNameFunction])
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
	
	def IScope allEntityRelationshipsScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localEntityRelationships], [importedResourceNameFunction])
	}
	
	def IScope allEntityScalarDataPropertiesScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localEntityScalarDataProperties], [importedResourceNameFunction])
	}
	
	def IScope allScalarOneOfRestrictionsScope(TerminologyBox tbox) {
		terminologyScope(tbox, [localScalarOneOfRestrictions], [importedResourceNameFunction])
	}
	
	def <T extends Element> IScope bundleScope(
		Bundle bundle,
		Function<Bundle, Iterable<T>> localScopeFunction,
		Function<Pair<Bundle, T>, QualifiedName> nameFunction
	) {
		val ArrayList<IEObjectDescription> result = Lists.newArrayList()
		result.addAll(Scopes.scopedElementsFor(localScopeFunction.apply(bundle)))
		result.addAll(bundle.allImportedBundles.map[importedTbox|
			Scopes.scopedElementsFor(
				localScopeFunction.apply(importedTbox), 
				[importedThing| nameFunction.apply(Pair.of(importedTbox, importedThing)) ]
			)
		].flatten)
		new SimpleScope(result)
	}
	
}