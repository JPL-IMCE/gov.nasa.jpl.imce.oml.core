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

 
package gov.nasa.jpl.imce.oml.specification.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param graphUUID[1,1]
  * @param uuid[1,1]
  * @param name[1,1]
  * @param language[0,1]
  * @param length[0,1]
  * @param maxLength[0,1]
  * @param minLength[0,1]
  * @param pattern[0,1]
  * @param restrictedRangeUUID[1,1]
  */
case class PlainLiteralScalarRestriction
(
  @(JSExport @field) graphUUID: UUID,
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) name: LocalName,
  @(JSExport @field) language: scala.Option[Language],
  @(JSExport @field) length: scala.Option[scala.Int],
  @(JSExport @field) maxLength: scala.Option[scala.Int],
  @(JSExport @field) minLength: scala.Option[scala.Int],
  @(JSExport @field) pattern: scala.Option[Pattern],
  @(JSExport @field) restrictedRangeUUID: UUID
) {
  @JSExport
  def this(
    graphUUID: UUID,
    uuid: UUID,
    name: LocalName,
    restrictedRangeUUID: UUID)
  = this(
      graphUUID,
      uuid,
      name,
      None /* language */,
      None /* length */,
      None /* maxLength */,
      None /* minLength */,
      None /* pattern */,
      restrictedRangeUUID)

  def withLanguage(l: Language)	 
  : PlainLiteralScalarRestriction
  = copy(language=Some(l))
  
  def withLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(length=Some(l))
  
  def withMaxLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(maxLength=Some(l))
  
  def withMinLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(minLength=Some(l))
  
  def withPattern(l: Pattern)	 
  : PlainLiteralScalarRestriction
  = copy(pattern=Some(l))
  
  override val hashCode
  : scala.Int 
  = (graphUUID, uuid, name, language, length, maxLength, minLength, pattern, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: PlainLiteralScalarRestriction =>
  	  (this.graphUUID == that.graphUUID) &&
  	  (this.uuid == that.uuid) &&
  	  (this.name == that.name) &&
  	  (this.language == that.language) &&
  	  (this.length == that.length) &&
  	  (this.maxLength == that.maxLength) &&
  	  (this.minLength == that.minLength) &&
  	  (this.pattern == that.pattern) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)
    case _ =>
      false
  }
  
}

@JSExport
object PlainLiteralScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "PlainLiteralScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[PlainLiteralScalarRestriction]
  = upickle.default.macroW[PlainLiteralScalarRestriction]

  @JSExport
  def toJSON(c: PlainLiteralScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[PlainLiteralScalarRestriction]
  = upickle.default.macroR[PlainLiteralScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : PlainLiteralScalarRestriction
  = upickle.default.read[PlainLiteralScalarRestriction](c)

}	
