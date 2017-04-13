/**
 *
 * Copyright 2017 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
 package gov.nasa.jpl.imce.oml.model.extensions

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.impl.NameBasedGenerator

import com.google.common.collect.Lists
import gov.nasa.jpl.imce.oml.model.common.Extent
import gov.nasa.jpl.imce.oml.model.bundles.Bundle
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyGraph
import gov.nasa.jpl.imce.oml.model.terminologies.Aspect
import gov.nasa.jpl.imce.oml.model.terminologies.Concept
import gov.nasa.jpl.imce.oml.model.terminologies.Entity
import gov.nasa.jpl.imce.oml.model.terminologies.ReifiedRelationship
import gov.nasa.jpl.imce.oml.model.terminologies.SpecializationAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom
import org.eclipse.emf.ecore.util.EcoreUtil
import java.util.ArrayList
import java.util.UUID
import gov.nasa.jpl.imce.oml.model.common.Element
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBox
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBoxExtendsClosedWorldDefinitions
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBoxRefinement
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyNestingAxiom
import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityExistentialRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityUniversalRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyExistentialRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyParticularRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyUniversalRestrictionAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarOneOfLiteralAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.AspectSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ConceptSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.terminologies.ReifiedRelationshipSpecializationAxiom
import gov.nasa.jpl.imce.oml.model.bundles.AnonymousConceptTaxonomyAxiom
import gov.nasa.jpl.imce.oml.model.bundles.SpecificDisjointConceptAxiom
import gov.nasa.jpl.imce.oml.model.bundles.RootConceptTaxonomyAxiom
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstanceDomain
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstanceRange
import gov.nasa.jpl.imce.oml.model.descriptions.ScalarDataPropertyValue
import gov.nasa.jpl.imce.oml.model.descriptions.ConceptInstance
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstance
import gov.nasa.jpl.imce.oml.model.descriptions.DataStructureTuple
import gov.nasa.jpl.imce.oml.model.descriptions.StructuredDataPropertyValue
import gov.nasa.jpl.imce.oml.model.descriptions.UnreifiedRelationshipInstanceTuple
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataProperty
import gov.nasa.jpl.imce.oml.model.terminologies.EntityStructuredDataProperty
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarDataProperty
import gov.nasa.jpl.imce.oml.model.terminologies.StructuredDataProperty
import gov.nasa.jpl.imce.oml.model.terminologies.BinaryScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.IRIScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.NumericScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.PlainLiteralScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarOneOfRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.StringScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.SynonymScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.TimeScalarRestriction
import gov.nasa.jpl.imce.oml.model.terminologies.Scalar
import gov.nasa.jpl.imce.oml.model.terminologies.Structure
import gov.nasa.jpl.imce.oml.model.terminologies.UnreifiedRelationship

class OMLExtensions {
	
  	static def UUID namespaceUUID(String namespace) {
  		namespaceUUID(namespace, new java.util.ArrayList<Pair<String,String>>())
  	}
	
  	static def UUID namespaceUUID(
  		String namespace, 
  		Pair<String,String> factor1
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		namespaceUUID(namespace, factors)
  	}
	
  	static def UUID namespaceUUID(
  		String namespace, 
  		Pair<String,String> factor1,
  		Pair<String,String> factor2
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		factors.add(factor2)
  		namespaceUUID(namespace, factors)
  	}
	
  	static def UUID namespaceUUID(String namespace, Pair<String,String>[] factors) {
  		val name = namespace + factors.map[pair| pair.key+":"+pair.value].join(",")
  		Generators.nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL).generate(name)
  	}

  	static def UUID derivedUUID(String context) {
  		derivedUUID(context, new java.util.ArrayList<Pair<String,String>>())
  	}
  	
  	static def UUID derivedUUID(
  		String context, 
  		Pair<String,String> factor1
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		derivedUUID(context, factors)
  	}
	
  	static def UUID derivedUUID(
  		String context, 
  		Pair<String,String> factor1,
  		Pair<String,String> factor2
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		factors.add(factor2)
  		derivedUUID(context, factors)
  	}
	
  	static def UUID derivedUUID(
  		String context, 
  		Pair<String,String> factor1,
  		Pair<String,String> factor2,
  		Pair<String,String> factor3
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		factors.add(factor2)
  		factors.add(factor3)
  		derivedUUID(context, factors)
  	}
	
  	static def UUID derivedUUID(
  		String context, 
  		Pair<String,String> factor1,
  		Pair<String,String> factor2,
  		Pair<String,String> factor3,
  		Pair<String,String> factor4
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		factors.add(factor2)
  		factors.add(factor3)
  		factors.add(factor4)
  		derivedUUID(context, factors)
  	}
	
  	static def UUID derivedUUID(
  		String context, 
  		Pair<String,String> factor1,
  		Pair<String,String> factor2,
  		Pair<String,String> factor3,
  		Pair<String,String> factor4,
  		Pair<String,String> factor5
  	) {
  		val factors = new java.util.ArrayList<Pair<String,String>>()
  		factors.add(factor1)
  		factors.add(factor2)
  		factors.add(factor3)
  		factors.add(factor4)
  		factors.add(factor5)
  		derivedUUID(context, factors)
  	}
  	static def derivedUUID(String context, Pair<String,String>[] factors) {
  		val name = context + factors.map[pair| pair.key+":"+pair.value].join(",")
  		Generators.nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL).generate(name)
  	}
  	
	def Iterable<TerminologyBox> terminologies(Extent it) {
		it.modules.filter(TerminologyBox)
	}
	
	def Iterable<TerminologyGraph> terminologyGraphs(Extent it) {
		it.modules.filter(TerminologyGraph)
	}
	
	def Iterable<Bundle> bundles(Extent it) {
		it.modules.filter(Bundle)
	}
	
	def phasedResolveAll(Extent it) {
		
		// phase 1
		terminologyGraphs.forEach[
			boxAxioms.forEach[switch it {
				TerminologyExtensionAxiom:
					EcoreUtil.resolveAll(it)	
			}]
		]
		
		// phase 2
		terminologyGraphs.forEach[
			boxAxioms.forEach[switch it {
				SpecializationAxiom:
					EcoreUtil.resolveAll(it)	
			}]
		]
	}
	
	def Iterable<TerminologyBox> allImportedTerminologies(TerminologyBox it) {
		collectAllImportedTerminologies(Lists.newArrayList(it), Lists.newArrayList())
	}
	
	final def Iterable<TerminologyBox> collectAllImportedTerminologies(
		ArrayList<TerminologyBox> queue, 
		ArrayList<TerminologyBox> acc
	) {
		if (queue.isEmpty)
			return acc
		
		val tbox = queue.head
		queue.remove(tbox)
		
		val inc = tbox.boxAxioms.map[target]
		queue.addAll(inc)
		acc.addAll(inc)
		
		collectAllImportedTerminologies(queue, acc)
	}
	
	def Iterable<Entity> localEntities(TerminologyBox it) {
		boxStatements.filter(Entity)
	}
	
	def Iterable<Entity> allEntities(TerminologyBox it) {
		localEntities + allImportedTerminologies(it).map[localEntities].flatten
	}
	
	def Iterable<Aspect> localAspects(TerminologyBox it) {
		boxStatements.filter(Aspect)
	}
	
	def Iterable<Aspect> allAspects(TerminologyBox it) {
		localAspects + allImportedTerminologies(it).map[localAspects].flatten
	}
	
	def Iterable<Concept> localConcepts(TerminologyBox it) {
		boxStatements.filter(Concept)
	}
	
	def Iterable<Concept> allConcepts(TerminologyBox it) {
		localConcepts + allImportedTerminologies(it).map[localConcepts].flatten
	}
	
	def Iterable<ReifiedRelationship> localReifiedRelationships(TerminologyBox it) {
		boxStatements.filter(ReifiedRelationship)
	}
	
	def Iterable<ReifiedRelationship> allReifiedRelationships(TerminologyBox it) {
		localReifiedRelationships + allImportedTerminologies(it).map[localReifiedRelationships].flatten
	}
	
	static def String kind(Element e) {
		switch e {
			case DescriptionBox:
				'DescriptionBox'
			case TerminologyGraph:
				'TerminologyGraph'
			case Bundle:
				'Bundle'
			case DescriptionBoxExtendsClosedWorldDefinitions:
				'DescriptionBoxExtendsClosedWorldDefinitions'
			case DescriptionBoxRefinement:
				'DescriptionBoxRefinement'
			case ConceptDesignationTerminologyAxiom:
				'ConceptDesignationTerminologyAxiom'
			case TerminologyExtensionAxiom:
				'TerminologyExtensionAxiom'
			case TerminologyNestingAxiom:
				'TerminologyNestingAxiom'
			case BundledTerminologyAxiom:
				'BundledTerminologyAxiom'
			case EntityExistentialRestrictionAxiom:
				'EntityExistentialRestrictionAxiom'
			case EntityUniversalRestrictionAxiom:
				'EntityUniversalRestrictionAxiom'
			case EntityScalarDataPropertyExistentialRestrictionAxiom:
				'EntityScalarDataPropertyExistentialRestrictionAxiom'
			case EntityScalarDataPropertyParticularRestrictionAxiom:
				'EntityScalarDataPropertyParticularRestrictionAxiom'
			case EntityScalarDataPropertyUniversalRestrictionAxiom:
				'EntityScalarDataPropertyUniversalRestrictionAxiom'
			case ScalarOneOfLiteralAxiom:
				'ScalarOneOfLiteralAxiom'
			case AspectSpecializationAxiom:
				'AspectSpecializationAxiom'
			case ConceptSpecializationAxiom:
				'ConceptSpecializationAxiom'
			case ReifiedRelationshipSpecializationAxiom:
				'ReifiedRelationshipSpecializationAxiom'
			case AnonymousConceptTaxonomyAxiom:
				'AnonymousConceptTaxonomyAxiom'
			case SpecificDisjointConceptAxiom:
				'SpecificDisjointConceptAxiom'
			case RootConceptTaxonomyAxiom:
				'RootConceptTaxonomyAxiom'
			case ReifiedRelationshipInstanceDomain:
				'ReifiedRelationshipInstanceDomain'
			case ReifiedRelationshipInstanceRange:
				'ReifiedRelationshipInstanceRange'
			case ScalarDataPropertyValue:
				'ScalarDataPropertyValue'
			case ConceptInstance:
				'ConceptInstance'
			case ReifiedRelationshipInstance:
				'ReifiedRelationshipInstance'
			case DataStructureTuple:
				'DataStructureTuple'
			case StructuredDataPropertyValue:
				'StructuredDataPropertyValue'
			case UnreifiedRelationshipInstanceTuple:
				'UnreifiedRelationshipInstanceTuple'
			case EntityScalarDataProperty:
				'EntityScalarDataProperty'
			case EntityStructuredDataProperty:
				'EntityStructuredDataProperty'
			case ScalarDataProperty:
				'ScalarDataProperty'
			case StructuredDataProperty:
				'StructuredDataProperty'
			case BinaryScalarRestriction:
				'BinaryScalarRestriction'
			case IRIScalarRestriction:
				'IRIScalarRestriction'
			case NumericScalarRestriction:
				'NumericScalarRestriction'
			case PlainLiteralScalarRestriction:
				'PlainLiteralScalarRestriction'
			case ScalarOneOfRestriction:
				'ScalarOneOfRestriction'
			case StringScalarRestriction:
				'StringScalarRestriction'
			case SynonymScalarRestriction:
				'SynonymScalarRestriction'
			case TimeScalarRestriction:
				'TimeScalarRestriction'
			case Scalar:
				'Scalar'
			case Structure:
				'Structure'
			case Aspect:
				'Aspect'
			case Concept:
				'Concept'
			case ReifiedRelationship:
				'ReifiedRelationship'
			case UnreifiedRelationship:
				'UnreifiedRelationship'
			default:
				'Other'
		}
	}
}