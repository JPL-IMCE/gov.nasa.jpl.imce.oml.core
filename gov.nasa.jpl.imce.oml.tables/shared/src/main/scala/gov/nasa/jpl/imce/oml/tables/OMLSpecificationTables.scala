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


package gov.nasa.jpl.imce.oml.tables

import java.io.{File,InputStream}
import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipFile}

import scala.collection.immutable.{Map,Seq}
import scala.collection.JavaConversions._
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}
import scala.{Boolean,Unit}
import scala.Predef.ArrowAssoc

case class OMLSpecificationTables
(
  annotationProperties : Seq[AnnotationProperty] = Seq.empty,
  terminologyGraphs : Seq[TerminologyGraph] = Seq.empty,
  bundles : Seq[Bundle] = Seq.empty,
  conceptDesignationTerminologyAxioms : Seq[ConceptDesignationTerminologyAxiom] = Seq.empty,
  terminologyExtensionAxioms : Seq[TerminologyExtensionAxiom] = Seq.empty,
  terminologyNestingAxioms : Seq[TerminologyNestingAxiom] = Seq.empty,
  aspects : Seq[Aspect] = Seq.empty,
  concepts : Seq[Concept] = Seq.empty,
  reifiedRelationships : Seq[ReifiedRelationship] = Seq.empty,
  unreifiedRelationships : Seq[UnreifiedRelationship] = Seq.empty,
  scalars : Seq[Scalar] = Seq.empty,
  structures : Seq[Structure] = Seq.empty,
  binaryScalarRestrictions : Seq[BinaryScalarRestriction] = Seq.empty,
  iriScalarRestrictions : Seq[IRIScalarRestriction] = Seq.empty,
  numericScalarRestrictions : Seq[NumericScalarRestriction] = Seq.empty,
  plainLiteralScalarRestrictions : Seq[PlainLiteralScalarRestriction] = Seq.empty,
  scalarOneOfRestrictions : Seq[ScalarOneOfRestriction] = Seq.empty,
  stringScalarRestrictions : Seq[StringScalarRestriction] = Seq.empty,
  synonymScalarRestrictions : Seq[SynonymScalarRestriction] = Seq.empty,
  timeScalarRestrictions : Seq[TimeScalarRestriction] = Seq.empty,
  entityScalarDataProperties : Seq[EntityScalarDataProperty] = Seq.empty,
  entityStructuredDataProperties : Seq[EntityStructuredDataProperty] = Seq.empty,
  scalarDataProperties : Seq[ScalarDataProperty] = Seq.empty,
  structuredDataProperties : Seq[StructuredDataProperty] = Seq.empty,
  aspectSpecializationAxioms : Seq[AspectSpecializationAxiom] = Seq.empty,
  conceptSpecializationAxioms : Seq[ConceptSpecializationAxiom] = Seq.empty,
  reifiedRelationshipSpecializationAxioms : Seq[ReifiedRelationshipSpecializationAxiom] = Seq.empty,
  entityExistentialRestrictionAxioms : Seq[EntityExistentialRestrictionAxiom] = Seq.empty,
  entityUniversalRestrictionAxioms : Seq[EntityUniversalRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyExistentialRestrictionAxioms : Seq[EntityScalarDataPropertyExistentialRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyParticularRestrictionAxioms : Seq[EntityScalarDataPropertyParticularRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyUniversalRestrictionAxioms : Seq[EntityScalarDataPropertyUniversalRestrictionAxiom] = Seq.empty,
  scalarOneOfLiteralAxioms : Seq[ScalarOneOfLiteralAxiom] = Seq.empty,
  bundledTerminologyAxioms : Seq[BundledTerminologyAxiom] = Seq.empty,
  anonymousConceptTaxonomyAxioms : Seq[AnonymousConceptTaxonomyAxiom] = Seq.empty,
  rootConceptTaxonomyAxioms : Seq[RootConceptTaxonomyAxiom] = Seq.empty,
  specificDisjointConceptAxioms : Seq[SpecificDisjointConceptAxiom] = Seq.empty,
  conceptInstances : Seq[ConceptInstance] = Seq.empty,
  dataStructureTuples : Seq[DataStructureTuple] = Seq.empty,
  descriptionBoxes : Seq[DescriptionBox] = Seq.empty,
  descriptionBoxExtendsClosedWorldDefinitions : Seq[DescriptionBoxExtendsClosedWorldDefinitions] = Seq.empty,
  descriptionBoxRefinements : Seq[DescriptionBoxRefinement] = Seq.empty,
  reifiedRelationshipInstances : Seq[ReifiedRelationshipInstance] = Seq.empty,
  reifiedRelationshipInstanceDomains : Seq[ReifiedRelationshipInstanceDomain] = Seq.empty,
  reifiedRelationshipInstanceRanges : Seq[ReifiedRelationshipInstanceRange] = Seq.empty,
  scalarDataPropertyValues : Seq[ScalarDataPropertyValue] = Seq.empty,
  structuredDataPropertyValues : Seq[StructuredDataPropertyValue] = Seq.empty,
  unreifiedRelationshipInstanceTuples : Seq[UnreifiedRelationshipInstanceTuple] = Seq.empty,
  annotations: Map[AnnotationProperty, Seq[AnnotationEntry]] = Map.empty)
{
  def readAnnotationProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(annotationProperties = readJSonTable(is, AnnotationPropertyHelper.fromJSON))
  def readTerminologyGraphs(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyGraphs = readJSonTable(is, TerminologyGraphHelper.fromJSON))
  def readBundles(is: InputStream)
  : OMLSpecificationTables
  = copy(bundles = readJSonTable(is, BundleHelper.fromJSON))
  def readConceptDesignationTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptDesignationTerminologyAxioms = readJSonTable(is, ConceptDesignationTerminologyAxiomHelper.fromJSON))
  def readTerminologyExtensionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyExtensionAxioms = readJSonTable(is, TerminologyExtensionAxiomHelper.fromJSON))
  def readTerminologyNestingAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyNestingAxioms = readJSonTable(is, TerminologyNestingAxiomHelper.fromJSON))
  def readAspects(is: InputStream)
  : OMLSpecificationTables
  = copy(aspects = readJSonTable(is, AspectHelper.fromJSON))
  def readConcepts(is: InputStream)
  : OMLSpecificationTables
  = copy(concepts = readJSonTable(is, ConceptHelper.fromJSON))
  def readReifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationships = readJSonTable(is, ReifiedRelationshipHelper.fromJSON))
  def readUnreifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationships = readJSonTable(is, UnreifiedRelationshipHelper.fromJSON))
  def readScalars(is: InputStream)
  : OMLSpecificationTables
  = copy(scalars = readJSonTable(is, ScalarHelper.fromJSON))
  def readStructures(is: InputStream)
  : OMLSpecificationTables
  = copy(structures = readJSonTable(is, StructureHelper.fromJSON))
  def readBinaryScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(binaryScalarRestrictions = readJSonTable(is, BinaryScalarRestrictionHelper.fromJSON))
  def readIRIScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(iriScalarRestrictions = readJSonTable(is, IRIScalarRestrictionHelper.fromJSON))
  def readNumericScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(numericScalarRestrictions = readJSonTable(is, NumericScalarRestrictionHelper.fromJSON))
  def readPlainLiteralScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(plainLiteralScalarRestrictions = readJSonTable(is, PlainLiteralScalarRestrictionHelper.fromJSON))
  def readScalarOneOfRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfRestrictions = readJSonTable(is, ScalarOneOfRestrictionHelper.fromJSON))
  def readStringScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(stringScalarRestrictions = readJSonTable(is, StringScalarRestrictionHelper.fromJSON))
  def readSynonymScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(synonymScalarRestrictions = readJSonTable(is, SynonymScalarRestrictionHelper.fromJSON))
  def readTimeScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(timeScalarRestrictions = readJSonTable(is, TimeScalarRestrictionHelper.fromJSON))
  def readEntityScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataProperties = readJSonTable(is, EntityScalarDataPropertyHelper.fromJSON))
  def readEntityStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityStructuredDataProperties = readJSonTable(is, EntityStructuredDataPropertyHelper.fromJSON))
  def readScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataProperties = readJSonTable(is, ScalarDataPropertyHelper.fromJSON))
  def readStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataProperties = readJSonTable(is, StructuredDataPropertyHelper.fromJSON))
  def readAspectSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(aspectSpecializationAxioms = readJSonTable(is, AspectSpecializationAxiomHelper.fromJSON))
  def readConceptSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptSpecializationAxioms = readJSonTable(is, ConceptSpecializationAxiomHelper.fromJSON))
  def readReifiedRelationshipSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipSpecializationAxioms = readJSonTable(is, ReifiedRelationshipSpecializationAxiomHelper.fromJSON))
  def readEntityExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityExistentialRestrictionAxioms = readJSonTable(is, EntityExistentialRestrictionAxiomHelper.fromJSON))
  def readEntityUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityUniversalRestrictionAxioms = readJSonTable(is, EntityUniversalRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyExistentialRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyExistentialRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyParticularRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyParticularRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyUniversalRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyUniversalRestrictionAxiomHelper.fromJSON))
  def readScalarOneOfLiteralAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfLiteralAxioms = readJSonTable(is, ScalarOneOfLiteralAxiomHelper.fromJSON))
  def readBundledTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(bundledTerminologyAxioms = readJSonTable(is, BundledTerminologyAxiomHelper.fromJSON))
  def readAnonymousConceptTaxonomyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(anonymousConceptTaxonomyAxioms = readJSonTable(is, AnonymousConceptTaxonomyAxiomHelper.fromJSON))
  def readRootConceptTaxonomyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(rootConceptTaxonomyAxioms = readJSonTable(is, RootConceptTaxonomyAxiomHelper.fromJSON))
  def readSpecificDisjointConceptAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(specificDisjointConceptAxioms = readJSonTable(is, SpecificDisjointConceptAxiomHelper.fromJSON))
  def readConceptInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptInstances = readJSonTable(is, ConceptInstanceHelper.fromJSON))
  def readDataStructureTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(dataStructureTuples = readJSonTable(is, DataStructureTupleHelper.fromJSON))
  def readDescriptionBoxes(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxes = readJSonTable(is, DescriptionBoxHelper.fromJSON))
  def readDescriptionBoxExtendsClosedWorldDefinitions(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxExtendsClosedWorldDefinitions = readJSonTable(is, DescriptionBoxExtendsClosedWorldDefinitionsHelper.fromJSON))
  def readDescriptionBoxRefinements(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxRefinements = readJSonTable(is, DescriptionBoxRefinementHelper.fromJSON))
  def readReifiedRelationshipInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstances = readJSonTable(is, ReifiedRelationshipInstanceHelper.fromJSON))
  def readReifiedRelationshipInstanceDomains(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceDomains = readJSonTable(is, ReifiedRelationshipInstanceDomainHelper.fromJSON))
  def readReifiedRelationshipInstanceRanges(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceRanges = readJSonTable(is, ReifiedRelationshipInstanceRangeHelper.fromJSON))
  def readScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataPropertyValues = readJSonTable(is, ScalarDataPropertyValueHelper.fromJSON))
  def readStructuredDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataPropertyValues = readJSonTable(is, StructuredDataPropertyValueHelper.fromJSON))
  def readUnreifiedRelationshipInstanceTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationshipInstanceTuples = readJSonTable(is, UnreifiedRelationshipInstanceTupleHelper.fromJSON))
  
  def isEmpty: Boolean
  = annotationProperties.isEmpty &&
    terminologyGraphs.isEmpty &&
    bundles.isEmpty &&
    conceptDesignationTerminologyAxioms.isEmpty &&
    terminologyExtensionAxioms.isEmpty &&
    terminologyNestingAxioms.isEmpty &&
    aspects.isEmpty &&
    concepts.isEmpty &&
    reifiedRelationships.isEmpty &&
    unreifiedRelationships.isEmpty &&
    scalars.isEmpty &&
    structures.isEmpty &&
    binaryScalarRestrictions.isEmpty &&
    iriScalarRestrictions.isEmpty &&
    numericScalarRestrictions.isEmpty &&
    plainLiteralScalarRestrictions.isEmpty &&
    scalarOneOfRestrictions.isEmpty &&
    stringScalarRestrictions.isEmpty &&
    synonymScalarRestrictions.isEmpty &&
    timeScalarRestrictions.isEmpty &&
    entityScalarDataProperties.isEmpty &&
    entityStructuredDataProperties.isEmpty &&
    scalarDataProperties.isEmpty &&
    structuredDataProperties.isEmpty &&
    aspectSpecializationAxioms.isEmpty &&
    conceptSpecializationAxioms.isEmpty &&
    reifiedRelationshipSpecializationAxioms.isEmpty &&
    entityExistentialRestrictionAxioms.isEmpty &&
    entityUniversalRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyExistentialRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyParticularRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyUniversalRestrictionAxioms.isEmpty &&
    scalarOneOfLiteralAxioms.isEmpty &&
    bundledTerminologyAxioms.isEmpty &&
    anonymousConceptTaxonomyAxioms.isEmpty &&
    rootConceptTaxonomyAxioms.isEmpty &&
    specificDisjointConceptAxioms.isEmpty &&
    conceptInstances.isEmpty &&
    dataStructureTuples.isEmpty &&
    descriptionBoxes.isEmpty &&
    descriptionBoxExtendsClosedWorldDefinitions.isEmpty &&
    descriptionBoxRefinements.isEmpty &&
    reifiedRelationshipInstances.isEmpty &&
    reifiedRelationshipInstanceDomains.isEmpty &&
    reifiedRelationshipInstanceRanges.isEmpty &&
    scalarDataPropertyValues.isEmpty &&
    structuredDataPropertyValues.isEmpty &&
    unreifiedRelationshipInstanceTuples.isEmpty &&
    annotations.isEmpty
}

object OMLSpecificationTables {
	
  def createEmptyOMLSpecificationTables()
  : OMLSpecificationTables
  = new OMLSpecificationTables()
  
  def loadOMLSpecificationTables(omlSchemaJsonZipFile: File)
  : Try[OMLSpecificationTables]
  = nonFatalCatch[Try[OMLSpecificationTables]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
      val zipFile = new ZipFile(omlSchemaJsonZipFile)
      val omlTables =
        zipFile
        .getEntries
        .toIterable
        .par
         .aggregate(OMLSpecificationTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omlTables)
    }

  def mergeTables
  (t1: OMLSpecificationTables, t2: OMLSpecificationTables)
  : OMLSpecificationTables
  = OMLSpecificationTables(
      annotationProperties = t1.annotationProperties ++ t2.annotationProperties,
      terminologyGraphs = t1.terminologyGraphs ++ t2.terminologyGraphs,
      bundles = t1.bundles ++ t2.bundles,
      conceptDesignationTerminologyAxioms = t1.conceptDesignationTerminologyAxioms ++ t2.conceptDesignationTerminologyAxioms,
      terminologyExtensionAxioms = t1.terminologyExtensionAxioms ++ t2.terminologyExtensionAxioms,
      terminologyNestingAxioms = t1.terminologyNestingAxioms ++ t2.terminologyNestingAxioms,
      aspects = t1.aspects ++ t2.aspects,
      concepts = t1.concepts ++ t2.concepts,
      reifiedRelationships = t1.reifiedRelationships ++ t2.reifiedRelationships,
      unreifiedRelationships = t1.unreifiedRelationships ++ t2.unreifiedRelationships,
      scalars = t1.scalars ++ t2.scalars,
      structures = t1.structures ++ t2.structures,
      binaryScalarRestrictions = t1.binaryScalarRestrictions ++ t2.binaryScalarRestrictions,
      iriScalarRestrictions = t1.iriScalarRestrictions ++ t2.iriScalarRestrictions,
      numericScalarRestrictions = t1.numericScalarRestrictions ++ t2.numericScalarRestrictions,
      plainLiteralScalarRestrictions = t1.plainLiteralScalarRestrictions ++ t2.plainLiteralScalarRestrictions,
      scalarOneOfRestrictions = t1.scalarOneOfRestrictions ++ t2.scalarOneOfRestrictions,
      stringScalarRestrictions = t1.stringScalarRestrictions ++ t2.stringScalarRestrictions,
      synonymScalarRestrictions = t1.synonymScalarRestrictions ++ t2.synonymScalarRestrictions,
      timeScalarRestrictions = t1.timeScalarRestrictions ++ t2.timeScalarRestrictions,
      entityScalarDataProperties = t1.entityScalarDataProperties ++ t2.entityScalarDataProperties,
      entityStructuredDataProperties = t1.entityStructuredDataProperties ++ t2.entityStructuredDataProperties,
      scalarDataProperties = t1.scalarDataProperties ++ t2.scalarDataProperties,
      structuredDataProperties = t1.structuredDataProperties ++ t2.structuredDataProperties,
      aspectSpecializationAxioms = t1.aspectSpecializationAxioms ++ t2.aspectSpecializationAxioms,
      conceptSpecializationAxioms = t1.conceptSpecializationAxioms ++ t2.conceptSpecializationAxioms,
      reifiedRelationshipSpecializationAxioms = t1.reifiedRelationshipSpecializationAxioms ++ t2.reifiedRelationshipSpecializationAxioms,
      entityExistentialRestrictionAxioms = t1.entityExistentialRestrictionAxioms ++ t2.entityExistentialRestrictionAxioms,
      entityUniversalRestrictionAxioms = t1.entityUniversalRestrictionAxioms ++ t2.entityUniversalRestrictionAxioms,
      entityScalarDataPropertyExistentialRestrictionAxioms = t1.entityScalarDataPropertyExistentialRestrictionAxioms ++ t2.entityScalarDataPropertyExistentialRestrictionAxioms,
      entityScalarDataPropertyParticularRestrictionAxioms = t1.entityScalarDataPropertyParticularRestrictionAxioms ++ t2.entityScalarDataPropertyParticularRestrictionAxioms,
      entityScalarDataPropertyUniversalRestrictionAxioms = t1.entityScalarDataPropertyUniversalRestrictionAxioms ++ t2.entityScalarDataPropertyUniversalRestrictionAxioms,
      scalarOneOfLiteralAxioms = t1.scalarOneOfLiteralAxioms ++ t2.scalarOneOfLiteralAxioms,
      bundledTerminologyAxioms = t1.bundledTerminologyAxioms ++ t2.bundledTerminologyAxioms,
      anonymousConceptTaxonomyAxioms = t1.anonymousConceptTaxonomyAxioms ++ t2.anonymousConceptTaxonomyAxioms,
      rootConceptTaxonomyAxioms = t1.rootConceptTaxonomyAxioms ++ t2.rootConceptTaxonomyAxioms,
      specificDisjointConceptAxioms = t1.specificDisjointConceptAxioms ++ t2.specificDisjointConceptAxioms,
      conceptInstances = t1.conceptInstances ++ t2.conceptInstances,
      dataStructureTuples = t1.dataStructureTuples ++ t2.dataStructureTuples,
      descriptionBoxes = t1.descriptionBoxes ++ t2.descriptionBoxes,
      descriptionBoxExtendsClosedWorldDefinitions = t1.descriptionBoxExtendsClosedWorldDefinitions ++ t2.descriptionBoxExtendsClosedWorldDefinitions,
      descriptionBoxRefinements = t1.descriptionBoxRefinements ++ t2.descriptionBoxRefinements,
      reifiedRelationshipInstances = t1.reifiedRelationshipInstances ++ t2.reifiedRelationshipInstances,
      reifiedRelationshipInstanceDomains = t1.reifiedRelationshipInstanceDomains ++ t2.reifiedRelationshipInstanceDomains,
      reifiedRelationshipInstanceRanges = t1.reifiedRelationshipInstanceRanges ++ t2.reifiedRelationshipInstanceRanges,
      scalarDataPropertyValues = t1.scalarDataPropertyValues ++ t2.scalarDataPropertyValues,
      structuredDataPropertyValues = t1.structuredDataPropertyValues ++ t2.structuredDataPropertyValues,
      unreifiedRelationshipInstanceTuples = t1.unreifiedRelationshipInstanceTuples ++ t2.unreifiedRelationshipInstanceTuples,
      annotations = t1.annotations ++ t2.annotations)
  
  def readZipArchive
  (zipFile: ZipFile)
  (tables: OMLSpecificationTables, ze: ZipArchiveEntry)
  : OMLSpecificationTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case AnnotationPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnnotationProperties(is)
  	  case TerminologyGraphHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyGraphs(is)
  	  case BundleHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundles(is)
  	  case ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptDesignationTerminologyAxioms(is)
  	  case TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyExtensionAxioms(is)
  	  case TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyNestingAxioms(is)
  	  case AspectHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspects(is)
  	  case ConceptHelper.TABLE_JSON_FILENAME =>
  	    tables.readConcepts(is)
  	  case ReifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationships(is)
  	  case UnreifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationships(is)
  	  case ScalarHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalars(is)
  	  case StructureHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructures(is)
  	  case BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readBinaryScalarRestrictions(is)
  	  case IRIScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readIRIScalarRestrictions(is)
  	  case NumericScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readNumericScalarRestrictions(is)
  	  case PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readPlainLiteralScalarRestrictions(is)
  	  case ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfRestrictions(is)
  	  case StringScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readStringScalarRestrictions(is)
  	  case SynonymScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readSynonymScalarRestrictions(is)
  	  case TimeScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readTimeScalarRestrictions(is)
  	  case EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataProperties(is)
  	  case EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityStructuredDataProperties(is)
  	  case ScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataProperties(is)
  	  case StructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataProperties(is)
  	  case AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspectSpecializationAxioms(is)
  	  case ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptSpecializationAxioms(is)
  	  case ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSpecializationAxioms(is)
  	  case EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityExistentialRestrictionAxioms(is)
  	  case EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityUniversalRestrictionAxioms(is)
  	  case EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyExistentialRestrictionAxioms(is)
  	  case EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyParticularRestrictionAxioms(is)
  	  case EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyUniversalRestrictionAxioms(is)
  	  case ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfLiteralAxioms(is)
  	  case BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundledTerminologyAxioms(is)
  	  case AnonymousConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnonymousConceptTaxonomyAxioms(is)
  	  case RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readRootConceptTaxonomyAxioms(is)
  	  case SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readSpecificDisjointConceptAxioms(is)
  	  case ConceptInstanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptInstances(is)
  	  case DataStructureTupleHelper.TABLE_JSON_FILENAME =>
  	    tables.readDataStructureTuples(is)
  	  case DescriptionBoxHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxes(is)
  	  case DescriptionBoxExtendsClosedWorldDefinitionsHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxExtendsClosedWorldDefinitions(is)
  	  case DescriptionBoxRefinementHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxRefinements(is)
  	  case ReifiedRelationshipInstanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstances(is)
  	  case ReifiedRelationshipInstanceDomainHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstanceDomains(is)
  	  case ReifiedRelationshipInstanceRangeHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstanceRanges(is)
  	  case ScalarDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataPropertyValues(is)
  	  case StructuredDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataPropertyValues(is)
  	  case UnreifiedRelationshipInstanceTupleHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationshipInstanceTuples(is)
      case annotationPropertyIRI =>
        tables
          .annotationProperties
          .find(_.iri == annotationPropertyIRI)
          .fold[OMLSpecificationTables](tables) { ap =>
          val annotationPropertyTable = ap -> readJSonTable[AnnotationEntry](is, AnnotationEntryHelper.fromJSON)
          tables.copy(annotations = tables.annotations + annotationPropertyTable)
        }
    }
  }
  
  def saveOMLSpecificationTables
  (tables: OMLSpecificationTables,
   omlSchemaJsonZipFile: File)
  : Try[Unit]
  = nonFatalCatch[Try[Unit]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
  	  // @see http://www.oracle.com/technetwork/articles/java/compress-1565076.html
  	  val fos = new java.io.FileOutputStream(omlSchemaJsonZipFile)
  	  val bos = new java.io.BufferedOutputStream(fos, 100000)
  	  val cos = new java.util.zip.CheckedOutputStream(bos, new java.util.zip.Adler32())
  	  val zos = new java.util.zip.ZipOutputStream(new java.io.BufferedOutputStream(cos))
  
  	  zos.setMethod(java.util.zip.ZipOutputStream.DEFLATED)
  
      zos.putNextEntry(new java.util.zip.ZipEntry(AnnotationPropertyHelper.TABLE_JSON_FILENAME))
      tables.annotationProperties.foreach { t =>
         val line = AnnotationPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyGraphHelper.TABLE_JSON_FILENAME))
      tables.terminologyGraphs.foreach { t =>
         val line = TerminologyGraphHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundleHelper.TABLE_JSON_FILENAME))
      tables.bundles.foreach { t =>
         val line = BundleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptDesignationTerminologyAxioms.foreach { t =>
         val line = ConceptDesignationTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyExtensionAxioms.foreach { t =>
         val line = TerminologyExtensionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyNestingAxioms.foreach { t =>
         val line = TerminologyNestingAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectHelper.TABLE_JSON_FILENAME))
      tables.aspects.foreach { t =>
         val line = AspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptHelper.TABLE_JSON_FILENAME))
      tables.concepts.foreach { t =>
         val line = ConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationships.foreach { t =>
         val line = ReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationships.foreach { t =>
         val line = UnreifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarHelper.TABLE_JSON_FILENAME))
      tables.scalars.foreach { t =>
         val line = ScalarHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructureHelper.TABLE_JSON_FILENAME))
      tables.structures.foreach { t =>
         val line = StructureHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.binaryScalarRestrictions.foreach { t =>
         val line = BinaryScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(IRIScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.iriScalarRestrictions.foreach { t =>
         val line = IRIScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(NumericScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.numericScalarRestrictions.foreach { t =>
         val line = NumericScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.plainLiteralScalarRestrictions.foreach { t =>
         val line = PlainLiteralScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfRestrictions.foreach { t =>
         val line = ScalarOneOfRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StringScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.stringScalarRestrictions.foreach { t =>
         val line = StringScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SynonymScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.synonymScalarRestrictions.foreach { t =>
         val line = SynonymScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TimeScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.timeScalarRestrictions.foreach { t =>
         val line = TimeScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataProperties.foreach { t =>
         val line = EntityScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityStructuredDataProperties.foreach { t =>
         val line = EntityStructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.scalarDataProperties.foreach { t =>
         val line = ScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.structuredDataProperties.foreach { t =>
         val line = StructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.aspectSpecializationAxioms.foreach { t =>
         val line = AspectSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptSpecializationAxioms.foreach { t =>
         val line = ConceptSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSpecializationAxioms.foreach { t =>
         val line = ReifiedRelationshipSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityExistentialRestrictionAxioms.foreach { t =>
         val line = EntityExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityUniversalRestrictionAxioms.foreach { t =>
         val line = EntityUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyExistentialRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyUniversalRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfLiteralAxioms.foreach { t =>
         val line = ScalarOneOfLiteralAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.bundledTerminologyAxioms.foreach { t =>
         val line = BundledTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnonymousConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME))
      tables.anonymousConceptTaxonomyAxioms.foreach { t =>
         val line = AnonymousConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME))
      tables.rootConceptTaxonomyAxioms.foreach { t =>
         val line = RootConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME))
      tables.specificDisjointConceptAxioms.foreach { t =>
         val line = SpecificDisjointConceptAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptInstanceHelper.TABLE_JSON_FILENAME))
      tables.conceptInstances.foreach { t =>
         val line = ConceptInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DataStructureTupleHelper.TABLE_JSON_FILENAME))
      tables.dataStructureTuples.foreach { t =>
         val line = DataStructureTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxes.foreach { t =>
         val line = DescriptionBoxHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxExtendsClosedWorldDefinitionsHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxExtendsClosedWorldDefinitions.foreach { t =>
         val line = DescriptionBoxExtendsClosedWorldDefinitionsHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxRefinementHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxRefinements.foreach { t =>
         val line = DescriptionBoxRefinementHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstances.foreach { t =>
         val line = ReifiedRelationshipInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceDomainHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstanceDomains.foreach { t =>
         val line = ReifiedRelationshipInstanceDomainHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceRangeHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstanceRanges.foreach { t =>
         val line = ReifiedRelationshipInstanceRangeHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.scalarDataPropertyValues.foreach { t =>
         val line = ScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.structuredDataPropertyValues.foreach { t =>
         val line = StructuredDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipInstanceTupleHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationshipInstanceTuples.foreach { t =>
         val line = UnreifiedRelationshipInstanceTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      
      tables
        .annotationProperties
        .foreach { ap =>
          tables
            .annotations
            .get(ap)
            .foreach { as =>
              zos.putNextEntry(new java.util.zip.ZipEntry(ap.iri))
              as.foreach { a =>
                val line = AnnotationEntryHelper.toJSON(a)+"\n"
                zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
              }
              zos.closeEntry()
            }
        }
  
      zos.close()
  	  Success(())
  	}

}
