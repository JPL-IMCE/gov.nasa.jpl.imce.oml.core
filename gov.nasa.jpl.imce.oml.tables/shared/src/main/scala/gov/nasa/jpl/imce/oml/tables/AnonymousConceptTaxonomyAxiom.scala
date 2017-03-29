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

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param uuid[0,1]
  * @param bundleUUID[1,1]
  * @param disjointTaxonomyParentUUID[1,1]
  */
case class AnonymousConceptTaxonomyAxiom
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) bundleUUID: UUID,
  @(JSExport @field) disjointTaxonomyParentUUID: UUID
) {
  @JSExport
  def this(
    bundleUUID: UUID,
    disjointTaxonomyParentUUID: UUID)
  = this(
      None /* uuid */,
      bundleUUID,
      disjointTaxonomyParentUUID)

  def withUuid(l: UUID)	 
  : AnonymousConceptTaxonomyAxiom
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, bundleUUID, disjointTaxonomyParentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnonymousConceptTaxonomyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundleUUID == that.bundleUUID) &&
  	  (this.disjointTaxonomyParentUUID == that.disjointTaxonomyParentUUID)
    case _ =>
      false
  }
  
}

@JSExport
object AnonymousConceptTaxonomyAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AnonymousConceptTaxonomyAxioms.json"
  
  implicit val w
  : upickle.default.Writer[AnonymousConceptTaxonomyAxiom]
  = upickle.default.macroW[AnonymousConceptTaxonomyAxiom]

  @JSExport
  def toJSON(c: AnonymousConceptTaxonomyAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AnonymousConceptTaxonomyAxiom]
  = upickle.default.macroR[AnonymousConceptTaxonomyAxiom]

  @JSExport
  def fromJSON(c: String)
  : AnonymousConceptTaxonomyAxiom
  = upickle.default.read[AnonymousConceptTaxonomyAxiom](c)

}	
