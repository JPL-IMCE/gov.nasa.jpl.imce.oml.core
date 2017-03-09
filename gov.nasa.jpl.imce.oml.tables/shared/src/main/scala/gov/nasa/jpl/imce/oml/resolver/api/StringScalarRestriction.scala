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

package gov.nasa.jpl.imce.oml.resolver.api

/*
 * An OML StringScalarRestriction is a data range that specifies how one string scalar adds facet restrictions to another.
 * Applicable when the restricted scalar represents [OWL2 Strings].
 * 
 * The restricted scalar must be directly or indirectly a restriction of:
 * - [xsd:string]
 * - [xsd:normalizedString]
 * - [xsd:token]
 * - [xsd:language]
 * - [xsd:Name]
 * - [xsd:NCName]
 * - [xsd:NMTOKEN]
 * 
 * Facets:
 * - [xsd:length]
 * - [xsd:minLength]
 * - [xsd:maxLength]
 * - [xsd:pattern]
 */
trait StringScalarRestriction
  extends RestrictedDataRange
{

  /*
   * The length of the string
   */
  val length: scala.Option[scala.Int]
  /*
   * The minimum length of the string
   */
  val minLength: scala.Option[scala.Int]
  /*
   * The maximum length of the string
   */
  val maxLength: scala.Option[scala.Int]
  /*
   * The pattern of the string (https://www.w3.org/TR/xmlschema-2/#regexs)
   */
  val pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern]

  override def calculateUUID
  (): java.util.UUID
}
