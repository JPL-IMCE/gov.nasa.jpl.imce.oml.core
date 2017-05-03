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

import gov.nasa.jpl.imce.oml.dsl.util.OMLIRIValueConverter;
import gov.nasa.jpl.imce.oml.dsl.util.OMLQNAMEValueConverter;
import gov.nasa.jpl.imce.oml.dsl.util.OMLReferenceValueConverter;
import gov.nasa.jpl.imce.oml.dsl.util.OMLSL_COMMENTValueConverter;

public class OMLValueConverterService /* implements DefaultTerminalConverters  */{
  /* @Inject
   */private OMLReferenceValueConverter referenceValueConverter;
  
  /* @Inject
   */private OMLQNAMEValueConverter qnameValueConverter;
  
  /* @Inject
   */private /* QualifiedNameValueConverter */Object qualifiedNameValueConverter;
  
  /* @Inject
   */private OMLIRIValueConverter iriValueConverter;
  
  /* @Inject
   */private OMLSL_COMMENTValueConverter sl_CommentValueConverter;
  
  /* @Inject
   */private /* KeywordAlternativeConverter */Object validIDValueConverter;
  
  /* @Inject
   */private /* AbstractIDValueConverter */Object idValueConverter;
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object Reference() {
    return this.referenceValueConverter;
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object QNAME() {
    return this.qnameValueConverter;
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object QualifiedName() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLValueConverterService.qualifiedNameValueConverter refers to the missing type QualifiedNameValueConverter");
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object IRI() {
    return this.iriValueConverter;
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object SL_COMMENT() {
    return this.sl_CommentValueConverter;
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object ValidID() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLValueConverterService.validIDValueConverter refers to the missing type KeywordAlternativeConverter");
  }
  
  /* @ValueConverter()
   */public /* IValueConverter<String> */Object ID() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLValueConverterService.idValueConverter refers to the missing type AbstractIDValueConverter");
  }
}
