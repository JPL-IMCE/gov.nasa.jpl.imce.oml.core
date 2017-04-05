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
 * An OML NumericScalarRestriction is a data range that specifies how one numeric scalar range adds facet restrictions to another.
 * Applies when the restricted scalar represents [OWL2 Real Numbers, Decimal Numbers and Integers] or [OWL2 Floating-Point Numbers].
 * 
 * The restricted scalar must be directly or indirectly a restriction of:
 * - [owl:real]
 * - [owl:rational]
 * - [xsd:decimal]
 * - [xsd:integer]
 * - [xsd:nonNegativeInteger]
 * - [xsd:nonPositiveInteger]
 * - [xsd:positiveInteger]
 * - [xsd:negativeInteger]
 * - [xsd:long]
 * - [xsd:int]
 * - [xsd:short]
 * - [xsd:byte]
 * - [xsd:unsignedLong]
 * - [xsd:unsignedInt]
 * - [xsd:unsignedShort]
 * - [xsd:unsignedByte]
 * - [xsd:double]
 * - [xsd:float]
 * 
 * Facets:
 * - [xsd:minInclusive]
 * - [xsd:maxInclusive]
 * - [xsd:minExclusive]
 * - [xsd:maxExclusive]
 */
trait NumericScalarRestriction
  extends RestrictedDataRange
{

  /*
   * The inclusive minimum value in the range
   */
  val minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber]
  /*
   * The inclusive maximum value in the range
   */
  val maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber]
  /*
   * The exclusive minimum value in the range
   */
  val minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber]
  /*
   * The exclusive maximum value in the range
   */
  val maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber]
}
