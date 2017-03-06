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

import com.google.inject.Inject
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.scoping.impl.ImportNormalizer
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider

import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox
import gov.nasa.jpl.imce.oml.model.common.Extent
import org.eclipse.xtext.scoping.IScope
import gov.nasa.jpl.imce.oml.model.common.Annotation
import gov.nasa.jpl.imce.oml.model.terminologies.AspectSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ConceptSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.DataRelationshipFromEntity
import gov.nasa.jpl.imce.oml.model.terminologies.DataRelationshipFromStructure
import gov.nasa.jpl.imce.oml.model.terminologies.DataRelationshipToScalar
import gov.nasa.jpl.imce.oml.model.terminologies.DataRelationshipToStructure
import gov.nasa.jpl.imce.oml.model.bundles.DisjointUnionOfConceptsAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRelationship
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyExistentialRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyUniversalRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ReifiedRelationshipSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.RestrictedDataRange
import gov.nasa.jpl.imce.oml.model.bundles.RootConceptTaxonomyAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarOneOfLiteralAxiom
import gov.nasa.jpl.imce.oml.model.bundles.SpecificDisjointConceptAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyNestingAxiom
import gov.nasa.jpl.imce.oml.model.common.CommonPackage
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologiesPackage
import gov.nasa.jpl.imce.oml.model.bundles.BundlesPackage
import gov.nasa.jpl.imce.oml.model.graphs.GraphsPackage
import gov.nasa.jpl.imce.oml.dsl.util.OMLImportNormalizer

class OMLImportedNamespaceAwareLocalScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
	
	@Inject IQualifiedNameConverter qnc
		
	override def List<ImportNormalizer> getImportedNamespaceResolvers(EObject context, boolean ignoreCase) {
		val res = new ArrayList<ImportNormalizer>();
		switch context {
			Extent:
				for (ap : context.annotationProperties)
					res.add(new OMLImportNormalizer(qnc.toQualifiedName(ap.iri), ap.abbrevIRI))
			TerminologyBox: {
				for (ap : context.extent.annotationProperties) {
					res.add(new OMLImportNormalizer(qnc.toQualifiedName(ap.iri), ap.abbrevIRI))
				}
				for (e : context.boxAxioms) {
					res.add(new OMLImportNormalizer(qnc.toQualifiedName(e.target.iri()), e.target.name))
				}
			}
		}
		res.addAll(super.getImportedNamespaceResolvers(context, ignoreCase));
		
		
		return res;
	}
	
	@Inject extension OMLScopeExtensions
	
 	override getScope(EObject context, EReference reference) {
 		var IScope scope = null
		switch context {
 			Annotation:
 				if (reference == CommonPackage.eINSTANCE.annotation_Property)
					scope = scope_Annotation_property(context, reference)
					
			EntityRelationship:
				if (reference == TerminologiesPackage.eINSTANCE.entityRelationship_Source ||
					reference == TerminologiesPackage.eINSTANCE.entityRelationship_Target)
					scope = scope_EntityRelationship(context)
					
			AspectSpecializationAxiom:
				if (reference == TerminologiesPackage.eINSTANCE.aspectSpecializationAxiom_SubEntity)
					scope = scope_AspectSpecializationAxiom_subEntity(context)
				else if (reference == TerminologiesPackage.eINSTANCE.aspectSpecializationAxiom_SuperAspect)
					scope = scope_AspectSpecializationAxiom_superAspect(context)
			ConceptSpecializationAxiom:
				{}
			ReifiedRelationshipSpecializationAxiom:
				{}
			RestrictedDataRange:
				{}
				
			DataRelationshipFromEntity:
				{}
			DataRelationshipFromStructure:
				{}
			DataRelationshipToScalar:
				{}
			DataRelationshipToStructure:
				{}
				
			EntityRestrictionAxiom:
				{}
			EntityScalarDataPropertyExistentialRestrictionAxiom:
				{}
			EntityScalarDataPropertyUniversalRestrictionAxiom:
				{}
			EntityScalarDataPropertyRestrictionAxiom:
				{}
				
			ScalarOneOfLiteralAxiom:
				{}
				
			RootConceptTaxonomyAxiom:
				{}
			SpecificDisjointConceptAxiom:
				{}
			DisjointUnionOfConceptsAxiom:
				{}
				
			BundledTerminologyAxiom:
				if (reference == BundlesPackage.eINSTANCE.bundledTerminologyAxiom_BundledTerminology)
					scope = scope_BundledTerminologyAxiom_bundledTerminology(context)
			ConceptDesignationTerminologyAxiom:
				if (reference == GraphsPackage.eINSTANCE.conceptDesignationTerminologyAxiom_DesignatedTerminology)
					scope = scope_ConceptDesignationTerminologyAxiom_designatedTerminology(context)
				else if (reference == GraphsPackage.eINSTANCE.conceptDesignationTerminologyAxiom_DesignatedConcept)
					scope = scope_ConceptDesignationTerminologyAxiom_designatedConcept(context)
			TerminologyExtensionAxiom:
				if (reference == TerminologiesPackage.eINSTANCE.terminologyExtensionAxiom_ExtendedTerminology)
					scope = scope_TerminologyExtensionAxiom_extendedTerminology(context, reference)
			TerminologyNestingAxiom:
				{}
		} 
		if (null !== scope)
			scope
		else
			super.getScope(context, reference)
	}
	
}