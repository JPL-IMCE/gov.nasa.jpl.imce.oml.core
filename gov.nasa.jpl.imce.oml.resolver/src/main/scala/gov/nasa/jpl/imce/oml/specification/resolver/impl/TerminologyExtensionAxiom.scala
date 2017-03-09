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

package gov.nasa.jpl.imce.oml.specification.resolver.impl

import gov.nasa.jpl.imce.oml.specification._

case class TerminologyExtensionAxiom private[impl] 
(
 override val uuid: java.util.UUID,
 override val terminology: resolver.api.TerminologyBox,
 override val extendedTerminology: resolver.api.TerminologyBox
)
extends resolver.api.TerminologyExtensionAxiom
  with TerminologyBoxAxiom
{
  def extendingTerminology
  ()
  : resolver.api.TerminologyBox
  = {
    terminology
  }
  
  /*
   * The extendingTerminology is the source
   */
  override def source
  ()
  : resolver.api.TerminologyBox
  = {
    terminology
  }
  
  /*
   * The extendedTerminology is the target
   */
  override def target
  ()
  : resolver.api.TerminologyBox
  = {
    extendedTerminology
  }
  

  override def canEqual(that: scala.Any): scala.Boolean = that match {
  	case _: TerminologyExtensionAxiom => true
  	case _ => false
  }

  override val hashCode
  : scala.Int
  = (uuid, terminology, extendedTerminology).##

  override def equals(other: scala.Any): scala.Boolean = other match {
	  case that: TerminologyExtensionAxiom =>
	    (that canEqual this) &&
	    (this.uuid == that.uuid) &&
	    (this.terminology == that.terminology) &&
	    (this.extendedTerminology == that.extendedTerminology)

	  case _ =>
	    false
  }
}