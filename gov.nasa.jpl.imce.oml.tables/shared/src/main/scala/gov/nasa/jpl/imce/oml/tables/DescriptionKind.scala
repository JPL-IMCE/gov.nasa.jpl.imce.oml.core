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

import scala.scalajs.js.annotation.{JSExport, JSExportDescendentObjects}
import scala.Predef.String

@JSExportDescendentObjects
sealed trait DescriptionKind

case object Final extends DescriptionKind

case object Partial extends DescriptionKind

@JSExport
object DescriptionKind {

  @JSExport
  def toString(k: DescriptionKind)
  : String
  = k match {
    case Final =>
      "Final"
    case Partial =>
      "Partial"
  }

  implicit val w
  : upickle.default.Writer[DescriptionKind]
  = upickle.default.Writer[DescriptionKind]{ (k: DescriptionKind) =>
    upickle.Js.Str(toString(k))
  }

  @JSExport
  def toJSON(k: DescriptionKind)
  : String
  = upickle.default.write(expr=k, indent=0)

  @JSExport
  def fromString(k: String)
  : DescriptionKind
  = k match {
    case "Final" =>
      Final
    case "Partial" =>
      Partial
  }

  implicit val r
  : upickle.default.Reader[DescriptionKind]
  = upickle.default.Reader[DescriptionKind]{
    case upickle.Js.Str(k) =>
      fromString(k)
  }

  @JSExport
  def fromJSON(k: String)
  : DescriptionKind
  = upickle.default.read[DescriptionKind](k)

}
