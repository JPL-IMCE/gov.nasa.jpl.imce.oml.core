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
 package gov.nasa.jpl.imce.oml.dsl.tests

import com.google.inject.Inject
import com.itemis.xtext.testing.XtextRunner2
import com.itemis.xtext.testing.XtextTest
import jpl.imce.oml.specification.ecore.TerminologyExtent
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import com.google.inject.Provider
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.eclipse.xtext.formatting2.FormatterPreferenceKeys

@RunWith(XtextRunner2)
@InjectWith(OntologicalModelingLanguageInjectorProvider)
class OMLFileTests extends XtextTest {

	@Inject
	ParseHelper<TerminologyExtent> parseHelper

	@Inject extension FormatterTestHelper

	@Inject 
	extension ValidationTestHelper

	@Inject 
	Provider<XtextResourceSet> resourceSetProvider

	/*
	 * Tell the `testFile` method to verify
	 * that the result of parsing an OML test file 
	 * is precisely an OML TerminologyExtent
	 */
 	protected override Class<? extends EObject> getRootObjectType(URI uri) {
        return typeof(TerminologyExtent)
    }

	@Test 
	def void testBase() {
		val topURL = OMLFileTests.getResource("/")
		System.out.println("topURL="+topURL)
		
		val baseURL = OMLFileTests.getResource("/ModuleTests/base.oml")
		System.out.println("baseURL="+baseURL)
		
		testFile("ModuleTests/base.oml")
	}
	
	@Test 
	def void testMission() {
		val topURL = OMLFileTests.getResource("/")
		System.out.println("topURL="+topURL)
		
		val missionURL = OMLFileTests.getResource("/ModuleTests/mission.oml")
		System.out.println("missionURL="+missionURL)
		
		// need support for multi-file cross-references.
		//testFile("ModuleTests/mission.oml", "ModuleTests/base.oml")
		testFile("ModuleTests/mission.oml")
	}
	
	@Test 
	def void format1() {
		assertFormatted[
			preferences[
				put(FormatterPreferenceKeys.indentation, "\t")
				put(FormatterPreferenceKeys.tabWidth, 2)
			]
			expectation = '''
				
				open terminology <http://imce.jpl.nasa.gov/foundation/base/base> {
				aspect IdentifiedElement
				}
			'''
			toBeFormatted = '''
				 open 
				 
				  terminology 
				  
				    <http://imce.jpl.nasa.gov/foundation/base/base>       {
					
					    aspect 
					  IdentifiedElement
				}
			'''
		]
	}
}