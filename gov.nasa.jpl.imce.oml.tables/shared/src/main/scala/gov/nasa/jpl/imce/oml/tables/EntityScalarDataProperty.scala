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
import scala.scalajs.js.annotation.{JSExport,JSExportTopLevel}
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param tboxUUID[1,1]
  * @param domainUUID[1,1]
  * @param rangeUUID[1,1]
  * @param isIdentityCriteria[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("EntityScalarDataProperty")
case class EntityScalarDataProperty
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) domainUUID: UUID,
  @(JSExport @field) rangeUUID: UUID,
  @(JSExport @field) isIdentityCriteria: scala.Boolean,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    domainUUID: UUID,
    rangeUUID: UUID,
    isIdentityCriteria: scala.Boolean,
    name: LocalName)
  = this(
      oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString,
      tboxUUID,
      domainUUID,
      rangeUUID,
      isIdentityCriteria,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, domainUUID, rangeUUID, isIdentityCriteria, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityScalarDataProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.domainUUID == that.domainUUID) &&
  	  (this.rangeUUID == that.rangeUUID) &&
  	  (this.isIdentityCriteria == that.isIdentityCriteria) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyHelper")
object EntityScalarDataPropertyHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "EntityScalarDataPropertys.json"
  
  implicit val w
  : upickle.default.Writer[EntityScalarDataProperty]
  = upickle.default.macroW[EntityScalarDataProperty]

  @JSExport
  def toJSON(c: EntityScalarDataProperty)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[EntityScalarDataProperty]
  = upickle.default.macroR[EntityScalarDataProperty]

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataProperty
  = upickle.default.read[EntityScalarDataProperty](c)

}	
