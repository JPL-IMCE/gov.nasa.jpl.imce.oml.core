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

public class OMLReferenceValueConverter /* implements AbstractValueConverter<String>  */{
  /* @Inject
   */protected /* IValueConverterService */Object valueConverterService;
  
  public /* String */Object getDelegateRuleName() {
    return "QNAME";
  }
  
  public String toString(final /* String */Object value) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field OMLReferenceValueConverter.valueConverterService refers to the missing type IValueConverterService"
      + "\nThe method getDelegateRuleName() from the type OMLReferenceValueConverter refers to the missing type String"
      + "\nstartsWith cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nendsWith cannot be resolved"
      + "\ntoString cannot be resolved");
  }
  
  public String toValue(final /* String */Object string, final /* INode */Object node)/*  throws ValueConverterException */ {
    throw new Error("Unresolved compilation problems:"
      + "\nString cannot be resolved to a type."
      + "\nThe field OMLReferenceValueConverter.valueConverterService refers to the missing type IValueConverterService"
      + "\nThe method getDelegateRuleName() from the type OMLReferenceValueConverter refers to the missing type String"
      + "\nstartsWith cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nendsWith cannot be resolved"
      + "\ntoValue cannot be resolved");
  }
}
