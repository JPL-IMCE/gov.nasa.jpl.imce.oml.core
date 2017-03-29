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
  * @param refiningDescriptionBoxUUID[1,1]
  * @param refinedDescriptionBoxUUID[1,1]
  */
case class DescriptionBoxRefinement
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) refiningDescriptionBoxUUID: UUID,
  @(JSExport @field) refinedDescriptionBoxUUID: UUID
) {
  @JSExport
  def this(
    refiningDescriptionBoxUUID: UUID,
    refinedDescriptionBoxUUID: UUID)
  = this(
      None /* uuid */,
      refiningDescriptionBoxUUID,
      refinedDescriptionBoxUUID)

  def withUuid(l: UUID)	 
  : DescriptionBoxRefinement
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, refiningDescriptionBoxUUID, refinedDescriptionBoxUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DescriptionBoxRefinement =>
  	  (this.uuid == that.uuid) &&
  	  (this.refiningDescriptionBoxUUID == that.refiningDescriptionBoxUUID) &&
  	  (this.refinedDescriptionBoxUUID == that.refinedDescriptionBoxUUID)
    case _ =>
      false
  }
  
}

@JSExport
object DescriptionBoxRefinementHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "DescriptionBoxRefinements.json"
  
  implicit val w
  : upickle.default.Writer[DescriptionBoxRefinement]
  = upickle.default.macroW[DescriptionBoxRefinement]

  @JSExport
  def toJSON(c: DescriptionBoxRefinement)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[DescriptionBoxRefinement]
  = upickle.default.macroR[DescriptionBoxRefinement]

  @JSExport
  def fromJSON(c: String)
  : DescriptionBoxRefinement
  = upickle.default.read[DescriptionBoxRefinement](c)

}	
