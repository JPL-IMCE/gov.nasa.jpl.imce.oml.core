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
  * @param tboxUUID[1,1]
  * @param name[1,1]
  */
@JSExport
case class Scalar
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: Scalar =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExport
object ScalarHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "Scalars.json"
  
  implicit val w
  : upickle.default.Writer[Scalar]
  = upickle.default.macroW[Scalar]

  @JSExport
  def toJSON(c: Scalar)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[Scalar]
  = upickle.default.macroR[Scalar]

  @JSExport
  def fromJSON(c: String)
  : Scalar
  = upickle.default.read[Scalar](c)

}	
