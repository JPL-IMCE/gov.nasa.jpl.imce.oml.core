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
import org.eclipse.xtext.conversion.IValueConverterService
import org.eclipse.xtext.conversion.ValueConverterException
import org.eclipse.xtext.conversion.impl.AbstractValueConverter
import org.eclipse.xtext.nodemodel.INode

class OMLQNAMEValueConverter extends AbstractValueConverter<String> {
	
	@Inject
	protected IValueConverterService valueConverterService;

	def String getDelegateRuleName() {
		return "ValidID";
	}

	override toString(String value) {
		if (value.contains(':')) {
			val buffer = new StringBuilder()
			val segment = value.split(':')
			if (!segment.get(0).equals(""))
				buffer.append(valueConverterService.toString(segment.get(0), getDelegateRuleName()))
			buffer.append(':');
			buffer.append(valueConverterService.toString(segment.get(1), getDelegateRuleName()))
			return buffer.toString()
		} else
			return valueConverterService.toString(value, getDelegateRuleName())
	}
	
	override toValue(String string, INode node) throws ValueConverterException {
		if (string.contains(':')) {
			val buffer = new StringBuilder()
			val segment = string.split(':')
			if (!segment.get(0).equals(""))
				buffer.append(valueConverterService.toValue(segment.get(0), getDelegateRuleName(), null) as String)
			buffer.append(':');
			buffer.append(valueConverterService.toValue(segment.get(1), getDelegateRuleName(), null) as String)
			return buffer.toString()
		}  else
			return valueConverterService.toValue(string, getDelegateRuleName(), null) as String
	}
	
}