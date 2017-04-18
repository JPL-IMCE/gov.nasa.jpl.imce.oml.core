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
package gov.nasa.jpl.imce.oml.dsl.linking

import com.google.inject.Inject

import java.util.Collections

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.linking.impl.DefaultLinkingService
import org.eclipse.xtext.linking.impl.IllegalNodeException
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.resource.IEObjectDescription

import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty
import gov.nasa.jpl.imce.oml.model.common.Annotation
import gov.nasa.jpl.imce.oml.model.common.Element
import gov.nasa.jpl.imce.oml.model.extensions.OMLExtensions
import org.apache.xml.resolver.Catalog
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.common.util.URI
import gov.nasa.jpl.imce.oml.model.common.Extent
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBox
import gov.nasa.jpl.imce.oml.model.terminologies.Term
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologiesPackage
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionsPackage

/*
 * In the OML metamodel, an OML Annotation has 2 non-container references that require cross-reference resolution:
 * - property, which is specified in the concrete syntax grammar via an OML AnnotationProperty's abbreviated IRI
 * - subject, which is specified implicitly based on the fact that OML Annotations appear after an OML TerminologyThing subject.
 * 
 * The cross-reference resolution of the property in terms of its abbreviated IRI will trigger a call: getLinkedObjects(context, ref, node)
 * where ref corresponds to Annotation.property and node is the concrete syntax AST for the Annotation itself.
 * 
 * The concrete syntax for annotations looks like this ('...' means a repetition of the previous concrete syntax statement)
 * 
 * <TerminologyBox(tbox)> {
 *   <Annotation(subject=tbox, property=..., value=...)>
 *   ...
 *   <Annotation(subject=tbox, property=..., value=...)>
 * 
 *   <TerminologyThing(t1)>
 *   <Annotation(subject=t1, property=..., value=...)>
 *   ...
 *   <Annotation(subject=t1, property=..., value=...)>
 * 
 *   <TerminologyThing(t2)>
 *   <Annotation(subject=t2, property=..., value=...)>
 *   ...
 *   <Annotation(subject=t2, property=..., value=...)>
 * } 
 */
class OWLLinkingService extends DefaultLinkingService {
	
	@Inject
	private IQualifiedNameConverter qualifiedNameConverter
	
	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		val EClass requiredType = ref.getEReferenceType()
		if (null === requiredType)
			return Collections.<EObject>emptyList()

		val crossRefString = getCrossRefNodeAsString(node)

		// TODO: catalog-based cross-ref resolver.
		// when crossRefString = "<....>" 
		// look for a catalog from the current "file" directory and to the parent folders.
		// if found, use it to resolve the crossRef IRI to a local file; which needs to be loaded.
		if (null === crossRefString || crossRefString.equals(""))
			return Collections.emptyList()

		if (crossRefString.startsWith("<") && crossRefString.endsWith(">")) {
			val crossRefIRI = crossRefString.substring(1, crossRefString.length - 1)
			val fragmentIndex = crossRefIRI.indexOf('#')

			val Catalog catalog = OMLExtensions.findCatalogIfExists(context.eResource)
			if (null === catalog)
				return Collections.emptyList()

			val resourceIRI = if(-1 == fragmentIndex) crossRefIRI else crossRefIRI.substring(1, fragmentIndex - 1)
			val resourceFragment = if(-1 == fragmentIndex) null else crossRefIRI.substring(fragmentIndex + 1)
			val resolvedIRI = catalog.resolveURI(resourceIRI)
			if (null === resolvedIRI || resolvedIRI == resourceIRI)
				return Collections.emptyList()

			val rs = context.eResource.resourceSet
			if (!XtextResourceSet.isInstance(rs))
				return Collections.emptyList()

			val resolvedOML = rs.getResource(URI.createURI(resolvedIRI + ".oml"), true)
			val resolvedExt = resolvedOML.contents.filter(Extent).head
			val refType = ref.EType
			switch refType {
				case TerminologiesPackage.eINSTANCE.terminologyBox: {
					val tbox = resolvedExt.modules.filter(TerminologyBox).findFirst[tbox|tbox.iri() == resourceIRI]
					return if(null === tbox) Collections.emptyList() else Collections.singletonList(tbox)
				}
				case DescriptionsPackage.eINSTANCE.descriptionBox: {
					val dbox = resolvedExt.modules.filter(DescriptionBox).findFirst[dbox|dbox.iri() == resourceIRI]
					return if(null === dbox) Collections.emptyList() else Collections.singletonList(dbox)
				}
				case TerminologiesPackage.eINSTANCE.term:
					if (null === resourceFragment)
						return Collections.emptyList()
					else {
						val term = resolvedExt.modules.filter(TerminologyBox).map [
							boxStatements.filter(Term).findFirst[t|t.name() == resourceFragment]
						].filterNull.head
						return if(null === term) Collections.emptyList() else Collections.singletonList(term)
					}
				default:
					return Collections.emptyList()
			}
		}
		
		val IScope scope = getScope(context, ref)
		val QualifiedName qualifiedLinkName = qualifiedNameConverter.toQualifiedName(crossRefString)
		val IEObjectDescription eObjectDescription = scope.getSingleElement(qualifiedLinkName)
		if (null === eObjectDescription)
			return Collections.emptyList()

		val e = eObjectDescription.getEObjectOrProxy()

		switch context {
			// Piggy-back on the cross-reference resolution of the Annotation.property reference for an Annotation context
			// to lookup a TerminologyThing semantic element for the previous concrete syntax node of the Annotation node.
			// Since a TerminologyThing can have multiple subsequent Annotations in the concrete syntax, 
			// we rely on the fact that the cross-reference lookup will happen in the order in which Annotations appear in the concrete syntax.
			Annotation: {
				switch e {
					AnnotationProperty: {
						val prevNode = node.parent.previousSibling
						val prevSE = prevNode.leafNodes.head.semanticElement
						switch prevSE {
							Element:
								// In this case, the concrete syntax looks like this:
								// <TerminologyThing> == prevSE
								// <Annotation> == context
								context.subject = prevSE
							Annotation:
								// In this case, the concrete syntax looks like this:
								// <TerminologyThing>
								// <Annotation>
								// ...
								// <Annotation> == prevSE
								// <Annotation> == context
								context.subject = prevSE.subject
						}

						return Collections.singletonList(e)
					}
				}
			}
		}
		return Collections.singletonList(e)
	}
	
}