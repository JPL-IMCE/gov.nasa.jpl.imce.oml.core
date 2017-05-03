/**
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
package gov.nasa.jpl.imce.oml.dsl.scoping;

import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom;
import gov.nasa.jpl.imce.oml.model.common.Annotation;
import gov.nasa.jpl.imce.oml.model.common.Element;
import gov.nasa.jpl.imce.oml.model.common.Resource;
import gov.nasa.jpl.imce.oml.model.extensions.OMLExtensions;
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.AspectSpecializationAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRelationship;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom;

public class OMLScopeExtensions {
  /* @Inject
   */private OMLExtensions _oMLExtensions;
  
  /* @Inject
   */private /* IQualifiedNameConverter */Object qnc;
  
  /**
   * The syntax of Annotation involves "@<annotation property abbrev IRI> = <annotation value>".
   * Therefore, construct the resolvable scope of AnnotationProperties
   * in terms of the abbrevIRI of each AnnotationProperty in the TerminologyExtent.
   */
  public Object scope_Annotation_property(final Annotation annotation, final /* EReference */Object eRef) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field IScope is undefined"
      + "\nThe method getAnnotationProperties() from the type Extent refers to the missing type Object"
      + "\nThe field OMLScopeExtensions.qnc refers to the missing type IQualifiedNameConverter"
      + "\nscopeFor cannot be resolved"
      + "\ntoQualifiedName cannot be resolved"
      + "\nabbrevIRI cannot be resolved"
      + "\nNULLSCOPE cannot be resolved");
  }
  
  public IScope scope_AspectSpecializationAxiom_subEntity(final AspectSpecializationAxiom context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method allEntitiesScope(TerminologyBox) from the type OMLScopeExtensions refers to the missing type IScope");
  }
  
  public IScope scope_AspectSpecializationAxiom_superAspect(final AspectSpecializationAxiom context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method allAspectsScope(TerminologyBox) from the type OMLScopeExtensions refers to the missing type IScope");
  }
  
  public Object scope_BundledTerminologyAxiom_bundledTerminology(final BundledTerminologyAxiom context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field terminologies is undefined for the type Extent"
      + "\nThe method or field IScope is undefined"
      + "\nThe field OMLScopeExtensions.qnc refers to the missing type IQualifiedNameConverter"
      + "\nscopeFor cannot be resolved"
      + "\ntoQualifiedName cannot be resolved"
      + "\nnsPrefix cannot be resolved"
      + "\nNULLSCOPE cannot be resolved");
  }
  
  public Object scope_ConceptDesignationTerminologyAxiom_designatedTerminology(final ConceptDesignationTerminologyAxiom context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field terminologies is undefined for the type Extent"
      + "\nThe method or field IScope is undefined"
      + "\nThe field OMLScopeExtensions.qnc refers to the missing type IQualifiedNameConverter"
      + "\nscopeFor cannot be resolved"
      + "\ntoQualifiedName cannot be resolved"
      + "\nnsPrefix cannot be resolved"
      + "\nNULLSCOPE cannot be resolved");
  }
  
  public IScope scope_ConceptDesignationTerminologyAxiom_designatedConcept(final ConceptDesignationTerminologyAxiom context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method allConceptsScope(TerminologyBox) from the type OMLScopeExtensions refers to the missing type IScope");
  }
  
  public IScope scope_EntityRelationship(final EntityRelationship context) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method allEntitiesScope(TerminologyBox) from the type OMLScopeExtensions refers to the missing type IScope");
  }
  
  /**
   * The syntax of TerminologyExtensionAxioms involves "extends <extended terminology prefix>".
   * Therefore, construct the resolvable scope of TerminologyBoxes
   * in terms of the nsPrefix of each TerminologyBox in the TerminologyExtent.
   */
  public Object scope_TerminologyExtensionAxiom_extendedTerminology(final TerminologyExtensionAxiom context, final /* EReference */Object eRef) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field terminologyGraphs is undefined for the type Extent"
      + "\nThe method or field bundles is undefined for the type Extent"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field IScope is undefined"
      + "\nThe field OMLScopeExtensions.qnc refers to the missing type IQualifiedNameConverter"
      + "\n+ cannot be resolved"
      + "\nscopeFor cannot be resolved"
      + "\ntoQualifiedName cannot be resolved"
      + "\nnsPrefix cannot be resolved"
      + "\nNULLSCOPE cannot be resolved");
  }
  
  /**
   * Computes an IScope for
   */
  public <T extends Element> /* IScope */Object terminologyScope(final TerminologyBox tbox, final /* Function<TerminologyBox, Iterable<T>> */Object localScopeFunction, final /* Function<Pair<TerminologyBox, T>, QualifiedName> */Object nameFunction) {
    throw new Error("Unresolved compilation problems:"
      + "\nArrayList cannot be resolved to a type."
      + "\nThe method or field Lists is undefined"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field allImportedTerminologies is undefined for the type TerminologyBox"
      + "\nThe method or field Scopes is undefined"
      + "\nThe method or field Pair is undefined"
      + "\nSimpleScope cannot be resolved."
      + "\nIEObjectDescription cannot be resolved to a type."
      + "\nnewArrayList cannot be resolved"
      + "\naddAll cannot be resolved"
      + "\nscopedElementsFor cannot be resolved"
      + "\napply cannot be resolved"
      + "\naddAll cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nscopedElementsFor cannot be resolved"
      + "\napply cannot be resolved"
      + "\napply cannot be resolved"
      + "\nof cannot be resolved"
      + "\nflatten cannot be resolved");
  }
  
  public <T extends Resource> /* QualifiedName */Object importedResourceNameFunction(final /* Pair<TerminologyBox, T> */Object p) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLScopeExtensions.qnc refers to the missing type IQualifiedNameConverter"
      + "\ntoQualifiedName cannot be resolved"
      + "\nkey cannot be resolved"
      + "\nnsPrefix cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\nvalue cannot be resolved"
      + "\nname cannot be resolved");
  }
  
  public /* IScope */Object allEntitiesScope(final TerminologyBox tbox) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field localEntities is undefined"
      + "\nThe method terminologyScope(TerminologyBox, Function, Function) from the type OMLScopeExtensions refers to the missing type IScope"
      + "\nThe method importedResourceNameFunction(Pair) from the type OMLScopeExtensions refers to the missing type QualifiedName");
  }
  
  public /* IScope */Object allAspectsScope(final TerminologyBox tbox) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field localAspects is undefined"
      + "\nThe method terminologyScope(TerminologyBox, Function, Function) from the type OMLScopeExtensions refers to the missing type IScope"
      + "\nThe method importedResourceNameFunction(Pair) from the type OMLScopeExtensions refers to the missing type QualifiedName");
  }
  
  public /* IScope */Object allConceptsScope(final TerminologyBox tbox) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field localConcepts is undefined"
      + "\nThe method terminologyScope(TerminologyBox, Function, Function) from the type OMLScopeExtensions refers to the missing type IScope"
      + "\nThe method importedResourceNameFunction(Pair) from the type OMLScopeExtensions refers to the missing type QualifiedName");
  }
  
  public /* IScope */Object allReifiedRelationshipsScope(final TerminologyBox tbox) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field localReifiedRelationships is undefined"
      + "\nThe method terminologyScope(TerminologyBox, Function, Function) from the type OMLScopeExtensions refers to the missing type IScope"
      + "\nThe method importedResourceNameFunction(Pair) from the type OMLScopeExtensions refers to the missing type QualifiedName");
  }
}
