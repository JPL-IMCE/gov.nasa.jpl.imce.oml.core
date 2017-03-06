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
package gov.nasa.jpl.imce.oml.dsl.util

import com.google.inject.Inject
import gov.nasa.jpl.imce.oml.model.common.Resource
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.QualifiedName
import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty

class OMLQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	@Inject IQualifiedNameConverter qnc

	def QualifiedName qualifiedName(AnnotationProperty ap) {
		return qnc.toQualifiedName('<'+ap.iri+'>')
	}
	
	def QualifiedName qualifiedName(Resource resource) {
		return qnc.toQualifiedName('<'+resource.iri+'>')
	}
}