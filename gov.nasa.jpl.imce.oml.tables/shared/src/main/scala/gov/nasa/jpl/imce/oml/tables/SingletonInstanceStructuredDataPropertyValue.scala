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
  * @param descriptionBoxUUID[1,1]
  * @param singletonInstanceUUID[1,1]
  * @param structuredDataPropertyUUID[1,1]
  */
@JSExportTopLevel("SingletonInstanceStructuredDataPropertyValue")
case class SingletonInstanceStructuredDataPropertyValue
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) singletonInstanceUUID: UUID,
  @(JSExport @field) structuredDataPropertyUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: UUID,
    singletonInstanceUUID: UUID,
    structuredDataPropertyUUID: UUID)
  = this(
      oug.namespaceUUID(
        "SingletonInstanceStructuredDataPropertyValue",
        "descriptionBox" -> descriptionBoxUUID,
        "singletonInstance" -> singletonInstanceUUID,
        "structuredDataProperty" -> structuredDataPropertyUUID).toString,
      descriptionBoxUUID,
      singletonInstanceUUID,
      structuredDataPropertyUUID)

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonInstanceUUID, structuredDataPropertyUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SingletonInstanceStructuredDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.singletonInstanceUUID == that.singletonInstanceUUID) &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SingletonInstanceStructuredDataPropertyValueHelper")
object SingletonInstanceStructuredDataPropertyValueHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "SingletonInstanceStructuredDataPropertyValues.json"
  
  implicit val w
  : upickle.default.Writer[SingletonInstanceStructuredDataPropertyValue]
  = upickle.default.macroW[SingletonInstanceStructuredDataPropertyValue]

  @JSExport
  def toJSON(c: SingletonInstanceStructuredDataPropertyValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[SingletonInstanceStructuredDataPropertyValue]
  = upickle.default.macroR[SingletonInstanceStructuredDataPropertyValue]

  @JSExport
  def fromJSON(c: String)
  : SingletonInstanceStructuredDataPropertyValue
  = upickle.default.read[SingletonInstanceStructuredDataPropertyValue](c)

}	
