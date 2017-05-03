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
package gov.nasa.jpl.imce.oml.dsl.linking;

/**
 * In the OML metamodel, an OML Annotation has 2 non-container references that require cross-reference resolution:
 * - property, which is specified in the concrete syntax grammar via an OML AnnotationProperty's abbreviated IRI
 * - subject, which is specified implicitly based on the fact that OML Annotations appear after an OML TerminologyThing subject.
 * 
 * The cross-reference resolution of the property in terms of its abbreviated IRI will trigger a call: getLinkedObjects(context, ref, node)
 * where ref corresponds to Annotation.property and node is the concrete syntax AST for the Annotation itself.
 * 
 * The concrete syntax for annotations looks like this ('...' means a repetition of the previous concrete syntax statement)
 * 
 * <TerminologyBox(tbox)> {
 *   <Annotation(subject=tbox, property=..., value=...)>
 *   ...
 *   <Annotation(subject=tbox, property=..., value=...)>
 * 
 *   <TerminologyThing(t1)>
 *   <Annotation(subject=t1, property=..., value=...)>
 *   ...
 *   <Annotation(subject=t1, property=..., value=...)>
 * 
 *   <TerminologyThing(t2)>
 *   <Annotation(subject=t2, property=..., value=...)>
 *   ...
 *   <Annotation(subject=t2, property=..., value=...)>
 * }
 */
public class OWLLinkingService /* implements DefaultLinkingService  */{
  /* @Inject
   */private /* IQualifiedNameConverter */Object qualifiedNameConverter;
  
  public Object getLinkedObjects(final /* EObject */Object context, final /* EReference */Object ref, final /* INode */Object node)/*  throws IllegalNodeException */ {
    throw new Error("Unresolved compilation problems:"
      + "\nEClass cannot be resolved to a type."
      + "\nIScope cannot be resolved to a type."
      + "\nQualifiedName cannot be resolved to a type."
      + "\nIEObjectDescription cannot be resolved to a type."
      + "\n=== cannot be resolved."
      + "\nThe method or field Collections is undefined"
      + "\nThe method getCrossRefNodeAsString(INode) is undefined"
      + "\n!== cannot be resolved."
      + "\nThe method getScope(EObject, EReference) is undefined"
      + "\n!== cannot be resolved."
      + "\nThe method or field Collections is undefined"
      + "\nThe method or field Collections is undefined"
      + "\nThe method or field Collections is undefined"
      + "\nEObject cannot be resolved to a type."
      + "\nThe field OWLLinkingService.qualifiedNameConverter refers to the missing type IQualifiedNameConverter"
      + "\ngetEReferenceType cannot be resolved"
      + "\nemptyList cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nequals cannot be resolved"
      + "\n! cannot be resolved"
      + "\ntoQualifiedName cannot be resolved"
      + "\ngetSingleElement cannot be resolved"
      + "\ngetEObjectOrProxy cannot be resolved"
      + "\nparent cannot be resolved"
      + "\npreviousSibling cannot be resolved"
      + "\nleafNodes cannot be resolved"
      + "\nhead cannot be resolved"
      + "\nsemanticElement cannot be resolved"
      + "\nsubject cannot be resolved"
      + "\nsubject cannot be resolved"
      + "\nsubject cannot be resolved"
      + "\nsingletonList cannot be resolved"
      + "\nsingletonList cannot be resolved"
      + "\nemptyList cannot be resolved");
  }
}
