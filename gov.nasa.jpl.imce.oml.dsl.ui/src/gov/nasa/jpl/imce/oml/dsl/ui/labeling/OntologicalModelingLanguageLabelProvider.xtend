/*
 * Copyright 2016 California Institute of Technology (\"Caltech\").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the \"License\");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an \"AS IS\" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nasa.jpl.imce.oml.dsl.ui.labeling

import com.google.inject.Inject
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider
import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty
import gov.nasa.jpl.imce.oml.model.terminologies.Term
import gov.nasa.jpl.imce.oml.model.extensions.OMLExtensions
import gov.nasa.jpl.imce.oml.model.common.ModuleEdge
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBox
import gov.nasa.jpl.imce.oml.model.bundles.Bundle
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyGraph
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyExistentialRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyUniversalRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyParticularRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarOneOfLiteralAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.SpecializationAxiom
import gov.nasa.jpl.imce.oml.model.descriptions.ConceptualEntitySingletonInstance

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
class OntologicalModelingLanguageLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	def text(AnnotationProperty e) {
		'AnnotationProperty<'+e.iri+'>'
	}
	
	def text(Term e) {
		OMLExtensions.kind(e)+'<'+e.iri()+'>'
	}
	
	def text(ConceptualEntitySingletonInstance e) {
		OMLExtensions.kind(e)+'<'+e.iri()+'>'
	}
	
	def text(ModuleEdge e) {
		OMLExtensions.kind(e)+'('+e.sourceModule().iri()+'->'+e.targetModule().iri()+')'
	}
	
	def text(DescriptionBox m) {
		m.kind.toString+' '+OMLExtensions.kind(m)+'<'+m.iri()+'>'
	}
	
	def text(Bundle m) {
		m.kind.toString+' '+OMLExtensions.kind(m)+'<'+m.iri()+'>'
	}
	
	def text(TerminologyGraph m) {
		m.kind.toString+' '+OMLExtensions.kind(m)+'<'+m.iri()+'>'
	}
	
	def text(EntityRestrictionAxiom ax) {
		OMLExtensions.kind(ax)+'('+ax.restrictedRelation.iri()+') : '+ax.restrictedDomain.iri() + ' -> '+ax.restrictedRange.iri()
	}
	
	def text(EntityScalarDataPropertyExistentialRestrictionAxiom ax) {
		OMLExtensions.kind(ax)+'('+ax.scalarProperty.iri()+') : '+ax.restrictedEntity.iri() + ' -> '+ax.scalarRestriction.iri()
	}
	
	def text(EntityScalarDataPropertyUniversalRestrictionAxiom ax) {
		OMLExtensions.kind(ax)+'('+ax.scalarProperty.iri()+') : '+ax.restrictedEntity.iri() + ' -> '+ax.scalarRestriction.iri()
	}
	
	def text(EntityScalarDataPropertyParticularRestrictionAxiom ax) {
		OMLExtensions.kind(ax)+'('+ax.scalarProperty.iri()+') : '+ax.restrictedEntity.iri() + ' -> '+ax.literalValue
	}
	
	def text(ScalarOneOfLiteralAxiom ax) {
		OMLExtensions.kind(ax)+'('+text(ax.axiom)+') : '+ax.value
	}
	
	def text(SpecializationAxiom ax) {
		OMLExtensions.kind(ax)+' '+ax.child().iri()+' <: '+ax.parent().iri()
	}
	
	
	
	
	
}
