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
package gov.nasa.jpl.imce.oml.dsl.formatting2

import com.google.inject.Inject
import gov.nasa.jpl.imce.oml.model.common.Annotation
import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty
import gov.nasa.jpl.imce.oml.model.common.Extent
import gov.nasa.jpl.imce.oml.model.common.Module
import gov.nasa.jpl.imce.oml.dsl.services.OntologicalModelingLanguageGrammarAccess
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyGraph
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBoxAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBoxStatement
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import gov.nasa.jpl.imce.oml.model.terminologies.Aspect
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBox
import gov.nasa.jpl.imce.oml.model.bundles.TerminologyBundleAxiom
import gov.nasa.jpl.imce.oml.model.bundles.TerminologyBundleStatement
import gov.nasa.jpl.imce.oml.model.bundles.Bundle
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologiesPackage
import gov.nasa.jpl.imce.oml.model.common.CommonPackage

class OntologicalModelingLanguageFormatter extends AbstractFormatter2 {
	
	@Inject extension OntologicalModelingLanguageGrammarAccess

	def dispatch void format(Extent extent, extension IFormattableDocument document) {
		extent.prepend[noSpace]
		
		for (AnnotationProperty annotationProperty : extent.getAnnotationProperties()) {
			annotationProperty.format;
		}
		
		for (Module module : extent.getModules()) {
			module.format;
		}
	}

	def dispatch void format(AnnotationProperty annotationProperty, extension IFormattableDocument document) {
		annotationProperty.prepend[noSpace]
		
		annotationProperty.regionFor.ruleCall(annotationPropertyAccess.ANNOTATION_PROPERTY_TOKENTerminalRuleCall_0).append[oneSpace]
//		annotationProperty.regionFor.feature(CommonPackage.eINSTANCE.annotationProperty_AbbrevIRI).prepend[oneSpace]

		annotationProperty.regionFor.ruleCall(annotationPropertyAccess.EQUALTerminalRuleCall_2).surround[noSpace]
//		annotationProperty.regionFor.keyword("=").surround[noSpace]
		
		annotationProperty.regionFor.feature(CommonPackage.eINSTANCE.annotationProperty_Iri).append[newLine]
	}
	
	def dispatch void format(Annotation annotation, extension IFormattableDocument document) {
		annotation.prepend[noSpace]
		annotation.regionFor.ruleCall(annotationAccess.ANNOTATION_TOKENTerminalRuleCall_0).append[oneSpace]
//		annotation.regionFor.keyword("annotation").append[oneSpace]
		
		annotation.regionFor.ruleCall(annotationAccess.EQUALTerminalRuleCall_2).surround[noSpace]
//		annotation.regionFor.keyword("=").surround[noSpace]
	}
	
	def dispatch void format(TerminologyGraph terminologyGraph, extension IFormattableDocument document) {
		terminologyGraph.prepend[noSpace]
//		terminologyGraph.prepend[newLine]
			
		terminologyGraph.regionFor.feature(TerminologiesPackage.eINSTANCE.terminologyBox_Kind).append[oneSpace]
		
		terminologyGraph.regionFor.ruleCall(terminologyGraphAccess.TERMINOLOGY_GRAPH_TOKENTerminalRuleCall_1).surround[oneSpace]
//		terminologyGraph.regionFor.keyword("terminology").surround[oneSpace]
		
		terminologyGraph.regionFor.ruleCall(terminologyGraphAccess.iriIRITerminalRuleCall_2_0).surround[oneSpace]
//		terminologyGraph.regionFor.feature(CommonPackage.eINSTANCE.module_Iri).surround[oneSpace]
	
		val lcurly = terminologyGraph.regionFor.ruleCall(terminologyGraphAccess.LCURLYTerminalRuleCall_3)
//		val lcurly = terminologyGraph.regionFor.keyword("{")
		
		val rcurly = terminologyGraph.regionFor.ruleCall(terminologyGraphAccess.RCURLYTerminalRuleCall_5)
//		val rcurly = terminologyGraph.regionFor.keyword("}")
		
		lcurly.prepend[oneSpace]
		lcurly.append[newLine]
		interior(lcurly, rcurly)[indent]
		rcurly.append[newLine]
		
		for (Annotation annotations : terminologyGraph.getAnnotations()) {
			annotations.format
		}
		
		for (TerminologyBoxAxiom boxAxiom : terminologyGraph.getBoxAxioms()) {
			boxAxiom.format
		}
		
		for (TerminologyBoxStatement boxStatement : terminologyGraph.getBoxStatements()) {
			boxStatement.format
		}
	}
	

	def dispatch void format(Bundle bundle, extension IFormattableDocument document) {
		bundle.prepend[noSpace]
			
		val lcurly = bundle.regionFor.ruleCall(terminologyGraphAccess.LCURLYTerminalRuleCall_3)
//		val lcurly = bundle.regionFor.keyword("{")
		
		val rcurly = bundle.regionFor.ruleCall(terminologyGraphAccess.RCURLYTerminalRuleCall_5)
//		val rcurly = bundle.regionFor.keyword("}")
		
		lcurly.append[newLine]
		interior(lcurly, rcurly)[indent]
		
		for (Annotation annotations : bundle.getAnnotations()) {
			annotations.format
		}
		
		for (TerminologyBoxAxiom boxAxiom : bundle.getBoxAxioms()) {
			boxAxiom.format
		}
		
		for (TerminologyBoxStatement boxStatement : bundle.getBoxStatements()) {
			boxStatement.format
		}
		
		for (TerminologyBundleAxiom bundleAxiom : bundle.getBundleAxioms()) {
			bundleAxiom.format
		}
		
		for (TerminologyBundleStatement bundleStatement : bundle.getBundleStatements()) {
			bundleStatement.format
		}
	}
	
	def dispatch void format(DescriptionBox descriptionBox, extension IFormattableDocument document) {
		descriptionBox.prepend[noSpace]
			
		val lcurly = descriptionBox.regionFor.ruleCall(terminologyGraphAccess.LCURLYTerminalRuleCall_3)
//		val lcurly = descriptionBox.regionFor.keyword("{")
		
		val rcurly = descriptionBox.regionFor.ruleCall(terminologyGraphAccess.RCURLYTerminalRuleCall_5)
//		val rcurly = descriptionBox.regionFor.keyword("}")
		
		lcurly.append[newLine]
		interior(lcurly, rcurly)[indent]
		
		for (Annotation annotations : descriptionBox.getAnnotations()) {
			annotations.format
		}
	}

// The feature/keyword version produces undesirable results; the default is better although it doesn't collapse whitespace!
	def dispatch void format(Aspect aspect, extension IFormattableDocument document) {
		aspect.prepend[noSpace]
		
		aspect.regionFor.ruleCall(aspectAccess.ASPECT_TOKENTerminalRuleCall_0).append[oneSpace]
//		aspect.regionFor.keyword("aspect").append[oneSpace]
		
		aspect.regionFor.ruleCall(aspectAccess.nameIDTerminalRuleCall_1_0).append[newLine]
//		aspect.regionFor.feature(TerminologiesPackage.eINSTANCE.term_Name).append[newLine]
	}
	
// The feature/keyword version produces undesirable results; the default is better although it doesn't collapse whitespace!
	def dispatch void format(TerminologyExtensionAxiom ax, extension IFormattableDocument document) {
		ax.prepend[noSpace]
		
		ax.regionFor.ruleCall(terminologyExtensionAxiomAccess.EXTENDS_TOKENTerminalRuleCall_0).append[oneSpace]
//		ax.regionFor.keyword("extends").append[oneSpace]
		
		ax.regionFor.ruleCall(terminologyExtensionAxiomAccess.extendedTerminologyTerminologyBoxReferenceParserRuleCall_1_0_1).append[newLine]
//		ax.regionFor.feature(TerminologiesPackage.eINSTANCE.terminologyExtensionAxiom_ExtendedTerminology).append[newLine]
	}
}
