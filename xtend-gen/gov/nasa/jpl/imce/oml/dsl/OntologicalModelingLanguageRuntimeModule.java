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
package gov.nasa.jpl.imce.oml.dsl;

import gov.nasa.jpl.imce.oml.dsl.formatting2.OntologicalModelingLanguageFormatter;
import gov.nasa.jpl.imce.oml.dsl.linking.OWLLinkingService;
import gov.nasa.jpl.imce.oml.dsl.scoping.OMLImportedNamespaceAwareLocalScopeProvider;
import gov.nasa.jpl.imce.oml.dsl.util.OMLQualifiedNameConverter;
import gov.nasa.jpl.imce.oml.dsl.util.OMLQualifiedNameProvider;
import gov.nasa.jpl.imce.oml.dsl.util.OMLValueConverterService;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class OntologicalModelingLanguageRuntimeModule /* implements AbstractOntologicalModelingLanguageRuntimeModule  */{
  public /* Class<? extends ILinkingService> */Object bindILinkingService() {
    return OWLLinkingService.class;
  }
  
  public /* Class<? extends IScopeProvider> */Object bindIScopeProvider() {
    return OMLImportedNamespaceAwareLocalScopeProvider.class;
  }
  
  public /* Class<? extends IValueConverterService> */Object bindIValueConverterService() {
    return OMLValueConverterService.class;
  }
  
  public /* Class<? extends IQualifiedNameProvider> */Object bindIQualifiedNameProvider() {
    return OMLQualifiedNameProvider.class;
  }
  
  public /* Class<? extends IQualifiedNameConverter> */Object bindIQualifiedNameConverter() {
    return OMLQualifiedNameConverter.class;
  }
  
  public /* Class<? extends IGrammarAccess> */Object bindIGrammarAccess() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field OntologicalModelingLanguageGrammarAccess is undefined");
  }
  
  public /* Class<? extends IFormatter2> */Object bindIFormatter2() {
    return OntologicalModelingLanguageFormatter.class;
  }
}
