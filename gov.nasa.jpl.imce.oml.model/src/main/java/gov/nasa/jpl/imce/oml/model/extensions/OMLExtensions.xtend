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
}