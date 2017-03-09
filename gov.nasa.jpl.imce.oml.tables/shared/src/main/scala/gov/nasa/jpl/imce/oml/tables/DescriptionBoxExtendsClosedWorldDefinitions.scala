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
  * @param uuid[1,1]
  * @param descriptionBoxUUID[1,1]
  * @param closedWorldDefinitionsUUID[1,1]
  */
@JSExport
case class DescriptionBoxExtendsClosedWorldDefinitions
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) closedWorldDefinitionsUUID: UUID
) {
  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, closedWorldDefinitionsUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DescriptionBoxExtendsClosedWorldDefinitions =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.closedWorldDefinitionsUUID == that.closedWorldDefinitionsUUID)
    case _ =>
      false
  }
  
}

@JSExport
object DescriptionBoxExtendsClosedWorldDefinitionsHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "DescriptionBoxExtendsClosedWorldDefinitionss.json"
  
  implicit val w
  : upickle.default.Writer[DescriptionBoxExtendsClosedWorldDefinitions]
  = upickle.default.macroW[DescriptionBoxExtendsClosedWorldDefinitions]

  @JSExport
  def toJSON(c: DescriptionBoxExtendsClosedWorldDefinitions)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[DescriptionBoxExtendsClosedWorldDefinitions]
  = upickle.default.macroR[DescriptionBoxExtendsClosedWorldDefinitions]

  @JSExport
  def fromJSON(c: String)
  : DescriptionBoxExtendsClosedWorldDefinitions
  = upickle.default.read[DescriptionBoxExtendsClosedWorldDefinitions](c)

}	
