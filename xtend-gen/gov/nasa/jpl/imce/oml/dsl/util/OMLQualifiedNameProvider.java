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
package gov.nasa.jpl.imce.oml.dsl.util;

import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty;
import gov.nasa.jpl.imce.oml.model.common.Resource;

public class OMLQualifiedNameProvider /* implements DefaultDeclarativeQualifiedNameProvider  */{
  /* @Inject
   */private /* IQualifiedNameConverter */Object qnc;
  
  public /* QualifiedName */Object qualifiedName(final AnnotationProperty ap) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLQualifiedNameProvider.qnc refers to the missing type IQualifiedNameConverter"
      + "\nThe method getIri() from the type AnnotationProperty refers to the missing type Object"
      + "\ntoQualifiedName cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
  
  public /* QualifiedName */Object qualifiedName(final Resource resource) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLQualifiedNameProvider.qnc refers to the missing type IQualifiedNameConverter"
      + "\nThe method iri() from the type Resource refers to the missing type Object"
      + "\ntoQualifiedName cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
}
