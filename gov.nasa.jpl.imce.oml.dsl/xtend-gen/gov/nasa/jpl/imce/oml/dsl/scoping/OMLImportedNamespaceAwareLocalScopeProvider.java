/**
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
package gov.nasa.jpl.imce.oml.dsl.scoping;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import gov.nasa.jpl.imce.oml.dsl.scoping.OMLScopeExtensions;
import gov.nasa.jpl.imce.oml.dsl.util.OMLImportNormalizer;
import gov.nasa.jpl.imce.oml.model.bundles.Bundle;
import gov.nasa.jpl.imce.oml.model.bundles.BundledTerminologyAxiom;
import gov.nasa.jpl.imce.oml.model.bundles.BundlesPackage;
import gov.nasa.jpl.imce.oml.model.bundles.DisjointUnionOfConceptsAxiom;
import gov.nasa.jpl.imce.oml.model.bundles.RootConceptTaxonomyAxiom;
import gov.nasa.jpl.imce.oml.model.bundles.SpecificDisjointConceptAxiom;
import gov.nasa.jpl.imce.oml.model.bundles.TerminologyBundleAxiom;
import gov.nasa.jpl.imce.oml.model.common.Annotation;
import gov.nasa.jpl.imce.oml.model.common.AnnotationProperty;
import gov.nasa.jpl.imce.oml.model.common.CommonPackage;
import gov.nasa.jpl.imce.oml.model.common.Extent;
import gov.nasa.jpl.imce.oml.model.common.Module;
import gov.nasa.jpl.imce.oml.model.common.ModuleEdge;
import gov.nasa.jpl.imce.oml.model.descriptions.ConceptInstance;
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBox;
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBoxExtendsClosedWorldDefinitions;
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionBoxRefinement;
import gov.nasa.jpl.imce.oml.model.descriptions.DescriptionsPackage;
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstance;
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstanceDomain;
import gov.nasa.jpl.imce.oml.model.descriptions.ReifiedRelationshipInstanceRange;
import gov.nasa.jpl.imce.oml.model.descriptions.ScalarDataPropertyValue;
import gov.nasa.jpl.imce.oml.model.descriptions.SingletonInstanceScalarDataPropertyValue;
import gov.nasa.jpl.imce.oml.model.descriptions.SingletonInstanceStructuredDataPropertyValue;
import gov.nasa.jpl.imce.oml.model.descriptions.StructuredDataPropertyTuple;
import gov.nasa.jpl.imce.oml.model.descriptions.UnreifiedRelationshipInstanceTuple;
import gov.nasa.jpl.imce.oml.model.graphs.ConceptDesignationTerminologyAxiom;
import gov.nasa.jpl.imce.oml.model.graphs.GraphsPackage;
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyGraph;
import gov.nasa.jpl.imce.oml.model.graphs.TerminologyNestingAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.AspectSpecializationAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.ConceptSpecializationAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRelationship;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityRestrictionAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataProperty;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyExistentialRestrictionAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyParticularRestrictionAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityScalarDataPropertyUniversalRestrictionAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.EntityStructuredDataProperty;
import gov.nasa.jpl.imce.oml.model.terminologies.ReifiedRelationshipSpecializationAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.RestrictedDataRange;
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarDataProperty;
import gov.nasa.jpl.imce.oml.model.terminologies.ScalarOneOfLiteralAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.StructuredDataProperty;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologiesPackage;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBox;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyBoxAxiom;
import gov.nasa.jpl.imce.oml.model.terminologies.TerminologyExtensionAxiom;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OMLImportedNamespaceAwareLocalScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
  @Inject
  private IQualifiedNameConverter qnc;
  
  @Override
  public List<ImportNormalizer> getImportedNamespaceResolvers(final EObject context, final boolean ignoreCase) {
    final ArrayList<ImportNormalizer> res = new ArrayList<ImportNormalizer>();
    boolean _matched = false;
    if (context instanceof Extent) {
      _matched=true;
      EList<AnnotationProperty> _annotationProperties = ((Extent)context).getAnnotationProperties();
      for (final AnnotationProperty ap : _annotationProperties) {
        QualifiedName _qualifiedName = this.qnc.toQualifiedName(ap.getIri());
        String _abbrevIRI = ap.getAbbrevIRI();
        OMLImportNormalizer _oMLImportNormalizer = new OMLImportNormalizer(_qualifiedName, _abbrevIRI);
        res.add(_oMLImportNormalizer);
      }
    }
    if (!_matched) {
      if (context instanceof Bundle) {
        _matched=true;
        EList<AnnotationProperty> _annotationProperties = ((Bundle)context).getExtent().getAnnotationProperties();
        for (final AnnotationProperty ap : _annotationProperties) {
          QualifiedName _qualifiedName = this.qnc.toQualifiedName(ap.getIri());
          String _abbrevIRI = ap.getAbbrevIRI();
          OMLImportNormalizer _oMLImportNormalizer = new OMLImportNormalizer(_qualifiedName, _abbrevIRI);
          res.add(_oMLImportNormalizer);
        }
        EList<TerminologyBoxAxiom> _boxAxioms = ((Bundle)context).getBoxAxioms();
        for (final TerminologyBoxAxiom e : _boxAxioms) {
          String _elvis = null;
          TerminologyBox _target = null;
          if (e!=null) {
            _target=e.target();
          }
          String _iri = null;
          if (_target!=null) {
            _iri=_target.iri();
          }
          if (_iri != null) {
            _elvis = _iri;
          } else {
            _elvis = "";
          }
          QualifiedName _qualifiedName_1 = this.qnc.toQualifiedName(_elvis);
          String _elvis_1 = null;
          TerminologyBox _target_1 = e.target();
          String _name = null;
          if (_target_1!=null) {
            _name=_target_1.name();
          }
          if (_name != null) {
            _elvis_1 = _name;
          } else {
            _elvis_1 = "";
          }
          OMLImportNormalizer _oMLImportNormalizer_1 = new OMLImportNormalizer(_qualifiedName_1, _elvis_1);
          res.add(_oMLImportNormalizer_1);
        }
        EList<TerminologyBundleAxiom> _bundleAxioms = ((Bundle)context).getBundleAxioms();
        for (final TerminologyBundleAxiom e_1 : _bundleAxioms) {
          String _elvis_2 = null;
          TerminologyBox _target_2 = null;
          if (e_1!=null) {
            _target_2=e_1.target();
          }
          String _iri_1 = null;
          if (_target_2!=null) {
            _iri_1=_target_2.iri();
          }
          if (_iri_1 != null) {
            _elvis_2 = _iri_1;
          } else {
            _elvis_2 = "";
          }
          QualifiedName _qualifiedName_2 = this.qnc.toQualifiedName(_elvis_2);
          String _elvis_3 = null;
          TerminologyBox _target_3 = e_1.target();
          String _name_1 = null;
          if (_target_3!=null) {
            _name_1=_target_3.name();
          }
          if (_name_1 != null) {
            _elvis_3 = _name_1;
          } else {
            _elvis_3 = "";
          }
          OMLImportNormalizer _oMLImportNormalizer_2 = new OMLImportNormalizer(_qualifiedName_2, _elvis_3);
          res.add(_oMLImportNormalizer_2);
        }
      }
    }
    if (!_matched) {
      if (context instanceof TerminologyGraph) {
        _matched=true;
        EList<AnnotationProperty> _annotationProperties = ((TerminologyGraph)context).getExtent().getAnnotationProperties();
        for (final AnnotationProperty ap : _annotationProperties) {
          QualifiedName _qualifiedName = this.qnc.toQualifiedName(ap.getIri());
          String _abbrevIRI = ap.getAbbrevIRI();
          OMLImportNormalizer _oMLImportNormalizer = new OMLImportNormalizer(_qualifiedName, _abbrevIRI);
          res.add(_oMLImportNormalizer);
        }
        EList<TerminologyBoxAxiom> _boxAxioms = ((TerminologyGraph)context).getBoxAxioms();
        for (final TerminologyBoxAxiom e : _boxAxioms) {
          String _elvis = null;
          TerminologyBox _target = null;
          if (e!=null) {
            _target=e.target();
          }
          String _iri = null;
          if (_target!=null) {
            _iri=_target.iri();
          }
          if (_iri != null) {
            _elvis = _iri;
          } else {
            _elvis = "";
          }
          QualifiedName _qualifiedName_1 = this.qnc.toQualifiedName(_elvis);
          String _elvis_1 = null;
          TerminologyBox _target_1 = e.target();
          String _name = null;
          if (_target_1!=null) {
            _name=_target_1.name();
          }
          if (_name != null) {
            _elvis_1 = _name;
          } else {
            _elvis_1 = "";
          }
          OMLImportNormalizer _oMLImportNormalizer_1 = new OMLImportNormalizer(_qualifiedName_1, _elvis_1);
          res.add(_oMLImportNormalizer_1);
        }
      }
    }
    if (!_matched) {
      if (context instanceof DescriptionBox) {
        _matched=true;
        EList<AnnotationProperty> _annotationProperties = ((DescriptionBox)context).getExtent().getAnnotationProperties();
        for (final AnnotationProperty ap : _annotationProperties) {
          QualifiedName _qualifiedName = this.qnc.toQualifiedName(ap.getIri());
          String _abbrevIRI = ap.getAbbrevIRI();
          OMLImportNormalizer _oMLImportNormalizer = new OMLImportNormalizer(_qualifiedName, _abbrevIRI);
          res.add(_oMLImportNormalizer);
        }
        EList<DescriptionBoxExtendsClosedWorldDefinitions> _closedWorldDefinitions = ((DescriptionBox)context).getClosedWorldDefinitions();
        for (final DescriptionBoxExtendsClosedWorldDefinitions e : _closedWorldDefinitions) {
          String _elvis = null;
          TerminologyBox _closedWorldDefinitions_1 = null;
          if (e!=null) {
            _closedWorldDefinitions_1=e.getClosedWorldDefinitions();
          }
          String _iri = null;
          if (_closedWorldDefinitions_1!=null) {
            _iri=_closedWorldDefinitions_1.iri();
          }
          if (_iri != null) {
            _elvis = _iri;
          } else {
            _elvis = "";
          }
          QualifiedName _qualifiedName_1 = this.qnc.toQualifiedName(_elvis);
          String _elvis_1 = null;
          TerminologyBox _closedWorldDefinitions_2 = e.getClosedWorldDefinitions();
          String _name = null;
          if (_closedWorldDefinitions_2!=null) {
            _name=_closedWorldDefinitions_2.name();
          }
          if (_name != null) {
            _elvis_1 = _name;
          } else {
            _elvis_1 = "";
          }
          OMLImportNormalizer _oMLImportNormalizer_1 = new OMLImportNormalizer(_qualifiedName_1, _elvis_1);
          res.add(_oMLImportNormalizer_1);
        }
        EList<DescriptionBoxRefinement> _descriptionBoxRefinements = ((DescriptionBox)context).getDescriptionBoxRefinements();
        for (final DescriptionBoxRefinement e_1 : _descriptionBoxRefinements) {
          String _elvis_2 = null;
          DescriptionBox _refinedDescriptionBox = null;
          if (e_1!=null) {
            _refinedDescriptionBox=e_1.getRefinedDescriptionBox();
          }
          String _iri_1 = null;
          if (_refinedDescriptionBox!=null) {
            _iri_1=_refinedDescriptionBox.iri();
          }
          if (_iri_1 != null) {
            _elvis_2 = _iri_1;
          } else {
            _elvis_2 = "";
          }
          QualifiedName _qualifiedName_2 = this.qnc.toQualifiedName(_elvis_2);
          String _elvis_3 = null;
          DescriptionBox _refinedDescriptionBox_1 = e_1.getRefinedDescriptionBox();
          String _name_1 = null;
          if (_refinedDescriptionBox_1!=null) {
            _name_1=_refinedDescriptionBox_1.name();
          }
          if (_name_1 != null) {
            _elvis_3 = _name_1;
          } else {
            _elvis_3 = "";
          }
          OMLImportNormalizer _oMLImportNormalizer_2 = new OMLImportNormalizer(_qualifiedName_2, _elvis_3);
          res.add(_oMLImportNormalizer_2);
        }
      }
    }
    res.addAll(super.getImportedNamespaceResolvers(context, ignoreCase));
    return res;
  }
  
  @Inject
  @Extension
  private OMLScopeExtensions _oMLScopeExtensions;
  
  @Override
  protected String getImportedNamespace(final EObject object) {
    String _switchResult = null;
    boolean _matched = false;
    if (object instanceof ModuleEdge) {
      _matched=true;
      Module _targetModule = ((ModuleEdge)object).targetModule();
      String _iri = null;
      if (_targetModule!=null) {
        _iri=_targetModule.iri();
      }
      _switchResult = _iri;
    }
    if (!_matched) {
      _switchResult = super.getImportedNamespace(object);
    }
    return _switchResult;
  }
  
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    IScope _xblockexpression = null;
    {
      IScope scope = null;
      boolean _matched = false;
      if (context instanceof Annotation) {
        _matched=true;
        EReference _annotation_Property = CommonPackage.eINSTANCE.getAnnotation_Property();
        boolean _equals = Objects.equal(reference, _annotation_Property);
        if (_equals) {
          final IScope s1 = super.getScope(context, reference);
          final IScope s2 = this._oMLScopeExtensions.scope_Annotation_property(((Annotation)context), reference);
          final int n2 = IterableExtensions.size(s2.getAllElements());
          if ((n2 == 0)) {
            scope = s1;
          } else {
            scope = s2;
          }
        }
      }
      if (!_matched) {
        if (context instanceof TerminologyExtensionAxiom) {
          _matched=true;
          EReference _terminologyExtensionAxiom_ExtendedTerminology = TerminologiesPackage.eINSTANCE.getTerminologyExtensionAxiom_ExtendedTerminology();
          boolean _equals = Objects.equal(reference, _terminologyExtensionAxiom_ExtendedTerminology);
          if (_equals) {
            scope = this._oMLScopeExtensions.allTerminologies(((TerminologyExtensionAxiom)context).getTbox());
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityRelationship) {
          _matched=true;
          if ((Objects.equal(reference, TerminologiesPackage.eINSTANCE.getEntityRelationship_Source()) || 
            Objects.equal(reference, TerminologiesPackage.eINSTANCE.getEntityRelationship_Target()))) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityRelationship)context).getTbox());
          }
        }
      }
      if (!_matched) {
        if (context instanceof AspectSpecializationAxiom) {
          _matched=true;
          EReference _aspectSpecializationAxiom_SubEntity = TerminologiesPackage.eINSTANCE.getAspectSpecializationAxiom_SubEntity();
          boolean _equals = Objects.equal(reference, _aspectSpecializationAxiom_SubEntity);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((AspectSpecializationAxiom)context).getTbox());
          } else {
            EReference _aspectSpecializationAxiom_SuperAspect = TerminologiesPackage.eINSTANCE.getAspectSpecializationAxiom_SuperAspect();
            boolean _equals_1 = Objects.equal(reference, _aspectSpecializationAxiom_SuperAspect);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allAspectsScope(((AspectSpecializationAxiom)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof ConceptSpecializationAxiom) {
          _matched=true;
          EReference _conceptSpecializationAxiom_SubConcept = TerminologiesPackage.eINSTANCE.getConceptSpecializationAxiom_SubConcept();
          boolean _equals = Objects.equal(reference, _conceptSpecializationAxiom_SubConcept);
          if (_equals) {
            scope = this._oMLScopeExtensions.allConceptsScope(((ConceptSpecializationAxiom)context).getTbox());
          } else {
            EReference _conceptSpecializationAxiom_SuperConcept = TerminologiesPackage.eINSTANCE.getConceptSpecializationAxiom_SuperConcept();
            boolean _equals_1 = Objects.equal(reference, _conceptSpecializationAxiom_SuperConcept);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allConceptsScope(((ConceptSpecializationAxiom)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof ReifiedRelationshipSpecializationAxiom) {
          _matched=true;
          EReference _reifiedRelationshipSpecializationAxiom_SubRelationship = TerminologiesPackage.eINSTANCE.getReifiedRelationshipSpecializationAxiom_SubRelationship();
          boolean _equals = Objects.equal(reference, _reifiedRelationshipSpecializationAxiom_SubRelationship);
          if (_equals) {
            scope = this._oMLScopeExtensions.allReifiedRelationshipsScope(((ReifiedRelationshipSpecializationAxiom)context).getTbox());
          } else {
            EReference _reifiedRelationshipSpecializationAxiom_SuperRelationship = TerminologiesPackage.eINSTANCE.getReifiedRelationshipSpecializationAxiom_SuperRelationship();
            boolean _equals_1 = Objects.equal(reference, _reifiedRelationshipSpecializationAxiom_SuperRelationship);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allReifiedRelationshipsScope(((ReifiedRelationshipSpecializationAxiom)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof RestrictedDataRange) {
          _matched=true;
          EReference _restrictedDataRange_RestrictedRange = TerminologiesPackage.eINSTANCE.getRestrictedDataRange_RestrictedRange();
          boolean _equals = Objects.equal(reference, _restrictedDataRange_RestrictedRange);
          if (_equals) {
            scope = this._oMLScopeExtensions.allRangesScope(((RestrictedDataRange)context).getTbox());
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityScalarDataProperty) {
          _matched=true;
          EReference _dataRelationshipFromEntity_Domain = TerminologiesPackage.eINSTANCE.getDataRelationshipFromEntity_Domain();
          boolean _equals = Objects.equal(reference, _dataRelationshipFromEntity_Domain);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityScalarDataProperty)context).getTbox());
          } else {
            EReference _dataRelationshipToScalar_Range = TerminologiesPackage.eINSTANCE.getDataRelationshipToScalar_Range();
            boolean _equals_1 = Objects.equal(reference, _dataRelationshipToScalar_Range);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allRangesScope(((EntityScalarDataProperty)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityStructuredDataProperty) {
          _matched=true;
          EReference _dataRelationshipFromEntity_Domain = TerminologiesPackage.eINSTANCE.getDataRelationshipFromEntity_Domain();
          boolean _equals = Objects.equal(reference, _dataRelationshipFromEntity_Domain);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityStructuredDataProperty)context).getTbox());
          } else {
            EReference _dataRelationshipToStructure_Range = TerminologiesPackage.eINSTANCE.getDataRelationshipToStructure_Range();
            boolean _equals_1 = Objects.equal(reference, _dataRelationshipToStructure_Range);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allStructuresScope(((EntityStructuredDataProperty)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof ScalarDataProperty) {
          _matched=true;
          EReference _dataRelationshipFromStructure_Domain = TerminologiesPackage.eINSTANCE.getDataRelationshipFromStructure_Domain();
          boolean _equals = Objects.equal(reference, _dataRelationshipFromStructure_Domain);
          if (_equals) {
            scope = this._oMLScopeExtensions.allStructuresScope(((ScalarDataProperty)context).getTbox());
          } else {
            EReference _dataRelationshipToScalar_Range = TerminologiesPackage.eINSTANCE.getDataRelationshipToScalar_Range();
            boolean _equals_1 = Objects.equal(reference, _dataRelationshipToScalar_Range);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allRangesScope(((ScalarDataProperty)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof StructuredDataProperty) {
          _matched=true;
          EReference _dataRelationshipFromStructure_Domain = TerminologiesPackage.eINSTANCE.getDataRelationshipFromStructure_Domain();
          boolean _equals = Objects.equal(reference, _dataRelationshipFromStructure_Domain);
          if (_equals) {
            scope = this._oMLScopeExtensions.allStructuresScope(((StructuredDataProperty)context).getTbox());
          } else {
            EReference _dataRelationshipToStructure_Range = TerminologiesPackage.eINSTANCE.getDataRelationshipToStructure_Range();
            boolean _equals_1 = Objects.equal(reference, _dataRelationshipToStructure_Range);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allStructuresScope(((StructuredDataProperty)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityRestrictionAxiom) {
          _matched=true;
          EReference _entityRestrictionAxiom_RestrictedRelation = TerminologiesPackage.eINSTANCE.getEntityRestrictionAxiom_RestrictedRelation();
          boolean _equals = Objects.equal(reference, _entityRestrictionAxiom_RestrictedRelation);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntityRelationshipsScope(((EntityRestrictionAxiom)context).getTbox());
          } else {
            EReference _entityRestrictionAxiom_RestrictedDomain = TerminologiesPackage.eINSTANCE.getEntityRestrictionAxiom_RestrictedDomain();
            boolean _equals_1 = Objects.equal(reference, _entityRestrictionAxiom_RestrictedDomain);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allEntitiesScope(((EntityRestrictionAxiom)context).getTbox());
            } else {
              EReference _entityRestrictionAxiom_RestrictedRange = TerminologiesPackage.eINSTANCE.getEntityRestrictionAxiom_RestrictedRange();
              boolean _equals_2 = Objects.equal(reference, _entityRestrictionAxiom_RestrictedRange);
              if (_equals_2) {
                scope = this._oMLScopeExtensions.allEntitiesScope(((EntityRestrictionAxiom)context).getTbox());
              }
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityScalarDataPropertyExistentialRestrictionAxiom) {
          _matched=true;
          EReference _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_RestrictedEntity();
          boolean _equals = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityScalarDataPropertyExistentialRestrictionAxiom)context).getTbox());
          } else {
            EReference _entityScalarDataPropertyRestrictionAxiom_ScalarProperty = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_ScalarProperty();
            boolean _equals_1 = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_ScalarProperty);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allEntityScalarDataPropertiesScope(((EntityScalarDataPropertyExistentialRestrictionAxiom)context).getTbox());
            } else {
              EReference _entityScalarDataPropertyExistentialRestrictionAxiom_ScalarRestriction = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyExistentialRestrictionAxiom_ScalarRestriction();
              boolean _equals_2 = Objects.equal(reference, _entityScalarDataPropertyExistentialRestrictionAxiom_ScalarRestriction);
              if (_equals_2) {
                scope = this._oMLScopeExtensions.allRangesScope(((EntityScalarDataPropertyExistentialRestrictionAxiom)context).getTbox());
              }
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityScalarDataPropertyParticularRestrictionAxiom) {
          _matched=true;
          EReference _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_RestrictedEntity();
          boolean _equals = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityScalarDataPropertyParticularRestrictionAxiom)context).getTbox());
          } else {
            EReference _entityScalarDataPropertyRestrictionAxiom_ScalarProperty = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_ScalarProperty();
            boolean _equals_1 = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_ScalarProperty);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allEntityScalarDataPropertiesScope(((EntityScalarDataPropertyParticularRestrictionAxiom)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof EntityScalarDataPropertyUniversalRestrictionAxiom) {
          _matched=true;
          EReference _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_RestrictedEntity();
          boolean _equals = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_RestrictedEntity);
          if (_equals) {
            scope = this._oMLScopeExtensions.allEntitiesScope(((EntityScalarDataPropertyUniversalRestrictionAxiom)context).getTbox());
          } else {
            EReference _entityScalarDataPropertyRestrictionAxiom_ScalarProperty = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyRestrictionAxiom_ScalarProperty();
            boolean _equals_1 = Objects.equal(reference, _entityScalarDataPropertyRestrictionAxiom_ScalarProperty);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allEntityScalarDataPropertiesScope(((EntityScalarDataPropertyUniversalRestrictionAxiom)context).getTbox());
            } else {
              EReference _entityScalarDataPropertyUniversalRestrictionAxiom_ScalarRestriction = TerminologiesPackage.eINSTANCE.getEntityScalarDataPropertyUniversalRestrictionAxiom_ScalarRestriction();
              boolean _equals_2 = Objects.equal(reference, _entityScalarDataPropertyUniversalRestrictionAxiom_ScalarRestriction);
              if (_equals_2) {
                scope = this._oMLScopeExtensions.allRangesScope(((EntityScalarDataPropertyUniversalRestrictionAxiom)context).getTbox());
              }
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof ScalarOneOfLiteralAxiom) {
          _matched=true;
          EReference _scalarOneOfLiteralAxiom_Axiom = TerminologiesPackage.eINSTANCE.getScalarOneOfLiteralAxiom_Axiom();
          boolean _equals = Objects.equal(reference, _scalarOneOfLiteralAxiom_Axiom);
          if (_equals) {
            scope = this._oMLScopeExtensions.allScalarOneOfRestrictionsScope(((ScalarOneOfLiteralAxiom)context).getTbox());
          }
        }
      }
      if (!_matched) {
        if (context instanceof RootConceptTaxonomyAxiom) {
          _matched=true;
          EReference _rootConceptTaxonomyAxiom_Root = BundlesPackage.eINSTANCE.getRootConceptTaxonomyAxiom_Root();
          boolean _equals = Objects.equal(reference, _rootConceptTaxonomyAxiom_Root);
          if (_equals) {
            scope = this._oMLScopeExtensions.allConceptsScope(((RootConceptTaxonomyAxiom)context).getBundle());
          }
        }
      }
      if (!_matched) {
        if (context instanceof SpecificDisjointConceptAxiom) {
          _matched=true;
          EReference _specificDisjointConceptAxiom_DisjointLeaf = BundlesPackage.eINSTANCE.getSpecificDisjointConceptAxiom_DisjointLeaf();
          boolean _equals = Objects.equal(reference, _specificDisjointConceptAxiom_DisjointLeaf);
          if (_equals) {
            scope = this._oMLScopeExtensions.allConceptsScope(((SpecificDisjointConceptAxiom)context).getDisjointTaxonomyParent().bundleContainer());
          }
        }
      }
      if (!_matched) {
        if (context instanceof DisjointUnionOfConceptsAxiom) {
          _matched=true;
        }
      }
      if (!_matched) {
        if (context instanceof BundledTerminologyAxiom) {
          _matched=true;
          EReference _bundledTerminologyAxiom_BundledTerminology = BundlesPackage.eINSTANCE.getBundledTerminologyAxiom_BundledTerminology();
          boolean _equals = Objects.equal(reference, _bundledTerminologyAxiom_BundledTerminology);
          if (_equals) {
            scope = this._oMLScopeExtensions.scope_BundledTerminologyAxiom_bundledTerminology(((BundledTerminologyAxiom)context));
          }
        }
      }
      if (!_matched) {
        if (context instanceof ConceptDesignationTerminologyAxiom) {
          _matched=true;
          EReference _conceptDesignationTerminologyAxiom_DesignatedTerminology = GraphsPackage.eINSTANCE.getConceptDesignationTerminologyAxiom_DesignatedTerminology();
          boolean _equals = Objects.equal(reference, _conceptDesignationTerminologyAxiom_DesignatedTerminology);
          if (_equals) {
            scope = this._oMLScopeExtensions.scope_ConceptDesignationTerminologyAxiom_designatedTerminology(((ConceptDesignationTerminologyAxiom)context));
          } else {
            EReference _conceptDesignationTerminologyAxiom_DesignatedConcept = GraphsPackage.eINSTANCE.getConceptDesignationTerminologyAxiom_DesignatedConcept();
            boolean _equals_1 = Objects.equal(reference, _conceptDesignationTerminologyAxiom_DesignatedConcept);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.scope_ConceptDesignationTerminologyAxiom_designatedConcept(((ConceptDesignationTerminologyAxiom)context));
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof TerminologyNestingAxiom) {
          _matched=true;
          EReference _terminologyNestingAxiom_NestingTerminology = GraphsPackage.eINSTANCE.getTerminologyNestingAxiom_NestingTerminology();
          boolean _equals = Objects.equal(reference, _terminologyNestingAxiom_NestingTerminology);
          if (_equals) {
            scope = this._oMLScopeExtensions.allTerminologies(((TerminologyNestingAxiom)context).getTbox());
          } else {
            EReference _terminologyNestingAxiom_NestingContext = GraphsPackage.eINSTANCE.getTerminologyNestingAxiom_NestingContext();
            boolean _equals_1 = Objects.equal(reference, _terminologyNestingAxiom_NestingContext);
            if (_equals_1) {
              scope = this._oMLScopeExtensions.allConceptsScope(((TerminologyNestingAxiom)context).getTbox());
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof DescriptionBoxExtendsClosedWorldDefinitions) {
          _matched=true;
          EReference _descriptionBoxExtendsClosedWorldDefinitions_ClosedWorldDefinitions = DescriptionsPackage.eINSTANCE.getDescriptionBoxExtendsClosedWorldDefinitions_ClosedWorldDefinitions();
          boolean _equals = Objects.equal(reference, _descriptionBoxExtendsClosedWorldDefinitions_ClosedWorldDefinitions);
          if (_equals) {
            scope = this._oMLScopeExtensions.allTerminologies(((DescriptionBoxExtendsClosedWorldDefinitions)context).getDescriptionBox());
          }
        }
      }
      if (!_matched) {
        if (context instanceof DescriptionBoxRefinement) {
          _matched=true;
          EReference _descriptionBoxRefinement_RefinedDescriptionBox = DescriptionsPackage.eINSTANCE.getDescriptionBoxRefinement_RefinedDescriptionBox();
          boolean _equals = Objects.equal(reference, _descriptionBoxRefinement_RefinedDescriptionBox);
          if (_equals) {
            scope = this._oMLScopeExtensions.allDescriptions(((DescriptionBoxRefinement)context).descriptionDomain());
          }
        }
      }
      if (!_matched) {
        if (context instanceof SingletonInstanceScalarDataPropertyValue) {
          _matched=true;
          EReference _singletonInstanceScalarDataPropertyValue_SingletonInstance = DescriptionsPackage.eINSTANCE.getSingletonInstanceScalarDataPropertyValue_SingletonInstance();
          boolean _equals = Objects.equal(reference, _singletonInstanceScalarDataPropertyValue_SingletonInstance);
          if (_equals) {
            DescriptionBox _descriptionBox = ((SingletonInstanceScalarDataPropertyValue)context).descriptionBox();
            IScope _allConceptualEntitySingletonInstanceScope = null;
            if (_descriptionBox!=null) {
              _allConceptualEntitySingletonInstanceScope=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox);
            }
            scope = _allConceptualEntitySingletonInstanceScope;
          } else {
            EReference _singletonInstanceScalarDataPropertyValue_ScalarDataProperty = DescriptionsPackage.eINSTANCE.getSingletonInstanceScalarDataPropertyValue_ScalarDataProperty();
            boolean _equals_1 = Objects.equal(reference, _singletonInstanceScalarDataPropertyValue_ScalarDataProperty);
            if (_equals_1) {
              DescriptionBox _descriptionBox_1 = ((SingletonInstanceScalarDataPropertyValue)context).descriptionBox();
              IScope _allEntityScalarDataPropertiesScope = null;
              if (_descriptionBox_1!=null) {
                _allEntityScalarDataPropertiesScope=this._oMLScopeExtensions.allEntityScalarDataPropertiesScope(_descriptionBox_1);
              }
              scope = _allEntityScalarDataPropertiesScope;
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof SingletonInstanceStructuredDataPropertyValue) {
          _matched=true;
          EReference _singletonInstanceStructuredDataPropertyValue_SingletonInstance = DescriptionsPackage.eINSTANCE.getSingletonInstanceStructuredDataPropertyValue_SingletonInstance();
          boolean _equals = Objects.equal(reference, _singletonInstanceStructuredDataPropertyValue_SingletonInstance);
          if (_equals) {
            DescriptionBox _descriptionBox = ((SingletonInstanceStructuredDataPropertyValue)context).descriptionBox();
            IScope _allConceptualEntitySingletonInstanceScope = null;
            if (_descriptionBox!=null) {
              _allConceptualEntitySingletonInstanceScope=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox);
            }
            scope = _allConceptualEntitySingletonInstanceScope;
          } else {
            EReference _singletonInstanceStructuredDataPropertyContext_StructuredDataProperty = DescriptionsPackage.eINSTANCE.getSingletonInstanceStructuredDataPropertyContext_StructuredDataProperty();
            boolean _equals_1 = Objects.equal(reference, _singletonInstanceStructuredDataPropertyContext_StructuredDataProperty);
            if (_equals_1) {
              DescriptionBox _descriptionBox_1 = ((SingletonInstanceStructuredDataPropertyValue)context).descriptionBox();
              IScope _allEntityStructuredDataPropertiesScope = null;
              if (_descriptionBox_1!=null) {
                _allEntityStructuredDataPropertiesScope=this._oMLScopeExtensions.allEntityStructuredDataPropertiesScope(_descriptionBox_1);
              }
              scope = _allEntityStructuredDataPropertiesScope;
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof StructuredDataPropertyTuple) {
          _matched=true;
          EReference _singletonInstanceStructuredDataPropertyContext_StructuredDataProperty = DescriptionsPackage.eINSTANCE.getSingletonInstanceStructuredDataPropertyContext_StructuredDataProperty();
          boolean _equals = Objects.equal(reference, _singletonInstanceStructuredDataPropertyContext_StructuredDataProperty);
          if (_equals) {
            DescriptionBox _descriptionBox = ((StructuredDataPropertyTuple)context).descriptionBox();
            IScope _allStructuredDataPropertiesScope = null;
            if (_descriptionBox!=null) {
              _allStructuredDataPropertiesScope=this._oMLScopeExtensions.allStructuredDataPropertiesScope(_descriptionBox);
            }
            scope = _allStructuredDataPropertiesScope;
          }
        }
      }
      if (!_matched) {
        if (context instanceof ScalarDataPropertyValue) {
          _matched=true;
          EReference _scalarDataPropertyValue_ScalarDataProperty = DescriptionsPackage.eINSTANCE.getScalarDataPropertyValue_ScalarDataProperty();
          boolean _equals = Objects.equal(reference, _scalarDataPropertyValue_ScalarDataProperty);
          if (_equals) {
            DescriptionBox _descriptionBox = ((ScalarDataPropertyValue)context).descriptionBox();
            IScope _allScalarDataPropertiesScope = null;
            if (_descriptionBox!=null) {
              _allScalarDataPropertiesScope=this._oMLScopeExtensions.allScalarDataPropertiesScope(_descriptionBox);
            }
            scope = _allScalarDataPropertiesScope;
          }
        }
      }
      if (!_matched) {
        if (context instanceof ConceptInstance) {
          _matched=true;
          EReference _conceptInstance_SingletonConceptClassifier = DescriptionsPackage.eINSTANCE.getConceptInstance_SingletonConceptClassifier();
          boolean _equals = Objects.equal(reference, _conceptInstance_SingletonConceptClassifier);
          if (_equals) {
            DescriptionBox _descriptionBox = ((ConceptInstance)context).descriptionBox();
            IScope _allConceptsScope = null;
            if (_descriptionBox!=null) {
              _allConceptsScope=this._oMLScopeExtensions.allConceptsScope(_descriptionBox);
            }
            scope = _allConceptsScope;
          }
        }
      }
      if (!_matched) {
        if (context instanceof ReifiedRelationshipInstance) {
          _matched=true;
          EReference _reifiedRelationshipInstance_SingletonReifiedRelationshipClassifier = DescriptionsPackage.eINSTANCE.getReifiedRelationshipInstance_SingletonReifiedRelationshipClassifier();
          boolean _equals = Objects.equal(reference, _reifiedRelationshipInstance_SingletonReifiedRelationshipClassifier);
          if (_equals) {
            DescriptionBox _descriptionBox = ((ReifiedRelationshipInstance)context).descriptionBox();
            IScope _allReifiedRelationshipScope = null;
            if (_descriptionBox!=null) {
              _allReifiedRelationshipScope=this._oMLScopeExtensions.allReifiedRelationshipScope(_descriptionBox);
            }
            scope = _allReifiedRelationshipScope;
          }
        }
      }
      if (!_matched) {
        if (context instanceof ReifiedRelationshipInstanceDomain) {
          _matched=true;
          EReference _reifiedRelationshipInstanceDomain_ReifiedRelationshipInstance = DescriptionsPackage.eINSTANCE.getReifiedRelationshipInstanceDomain_ReifiedRelationshipInstance();
          boolean _equals = Objects.equal(reference, _reifiedRelationshipInstanceDomain_ReifiedRelationshipInstance);
          if (_equals) {
            DescriptionBox _descriptionBox = ((ReifiedRelationshipInstanceDomain)context).descriptionBox();
            IScope _allReifiedRelationshipInstancesScope = null;
            if (_descriptionBox!=null) {
              _allReifiedRelationshipInstancesScope=this._oMLScopeExtensions.allReifiedRelationshipInstancesScope(_descriptionBox);
            }
            scope = _allReifiedRelationshipInstancesScope;
          } else {
            EReference _reifiedRelationshipInstanceDomain_Domain = DescriptionsPackage.eINSTANCE.getReifiedRelationshipInstanceDomain_Domain();
            boolean _equals_1 = Objects.equal(reference, _reifiedRelationshipInstanceDomain_Domain);
            if (_equals_1) {
              DescriptionBox _descriptionBox_1 = ((ReifiedRelationshipInstanceDomain)context).descriptionBox();
              IScope _allConceptualEntitySingletonInstanceScope = null;
              if (_descriptionBox_1!=null) {
                _allConceptualEntitySingletonInstanceScope=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox_1);
              }
              scope = _allConceptualEntitySingletonInstanceScope;
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof ReifiedRelationshipInstanceRange) {
          _matched=true;
          EReference _reifiedRelationshipInstanceRange_ReifiedRelationshipInstance = DescriptionsPackage.eINSTANCE.getReifiedRelationshipInstanceRange_ReifiedRelationshipInstance();
          boolean _equals = Objects.equal(reference, _reifiedRelationshipInstanceRange_ReifiedRelationshipInstance);
          if (_equals) {
            DescriptionBox _descriptionBox = ((ReifiedRelationshipInstanceRange)context).descriptionBox();
            IScope _allReifiedRelationshipInstancesScope = null;
            if (_descriptionBox!=null) {
              _allReifiedRelationshipInstancesScope=this._oMLScopeExtensions.allReifiedRelationshipInstancesScope(_descriptionBox);
            }
            scope = _allReifiedRelationshipInstancesScope;
          } else {
            EReference _reifiedRelationshipInstanceRange_Range = DescriptionsPackage.eINSTANCE.getReifiedRelationshipInstanceRange_Range();
            boolean _equals_1 = Objects.equal(reference, _reifiedRelationshipInstanceRange_Range);
            if (_equals_1) {
              DescriptionBox _descriptionBox_1 = ((ReifiedRelationshipInstanceRange)context).descriptionBox();
              IScope _allConceptualEntitySingletonInstanceScope = null;
              if (_descriptionBox_1!=null) {
                _allConceptualEntitySingletonInstanceScope=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox_1);
              }
              scope = _allConceptualEntitySingletonInstanceScope;
            }
          }
        }
      }
      if (!_matched) {
        if (context instanceof UnreifiedRelationshipInstanceTuple) {
          _matched=true;
          EReference _unreifiedRelationshipInstanceTuple_UnreifiedRelationship = DescriptionsPackage.eINSTANCE.getUnreifiedRelationshipInstanceTuple_UnreifiedRelationship();
          boolean _equals = Objects.equal(reference, _unreifiedRelationshipInstanceTuple_UnreifiedRelationship);
          if (_equals) {
            DescriptionBox _descriptionBox = ((UnreifiedRelationshipInstanceTuple)context).descriptionBox();
            IScope _allUnreifiedRelationshipScope = null;
            if (_descriptionBox!=null) {
              _allUnreifiedRelationshipScope=this._oMLScopeExtensions.allUnreifiedRelationshipScope(_descriptionBox);
            }
            scope = _allUnreifiedRelationshipScope;
          } else {
            EReference _unreifiedRelationshipInstanceTuple_Domain = DescriptionsPackage.eINSTANCE.getUnreifiedRelationshipInstanceTuple_Domain();
            boolean _equals_1 = Objects.equal(reference, _unreifiedRelationshipInstanceTuple_Domain);
            if (_equals_1) {
              DescriptionBox _descriptionBox_1 = ((UnreifiedRelationshipInstanceTuple)context).descriptionBox();
              IScope _allConceptualEntitySingletonInstanceScope = null;
              if (_descriptionBox_1!=null) {
                _allConceptualEntitySingletonInstanceScope=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox_1);
              }
              scope = _allConceptualEntitySingletonInstanceScope;
            } else {
              EReference _unreifiedRelationshipInstanceTuple_Range = DescriptionsPackage.eINSTANCE.getUnreifiedRelationshipInstanceTuple_Range();
              boolean _equals_2 = Objects.equal(reference, _unreifiedRelationshipInstanceTuple_Range);
              if (_equals_2) {
                DescriptionBox _descriptionBox_2 = ((UnreifiedRelationshipInstanceTuple)context).descriptionBox();
                IScope _allConceptualEntitySingletonInstanceScope_1 = null;
                if (_descriptionBox_2!=null) {
                  _allConceptualEntitySingletonInstanceScope_1=this._oMLScopeExtensions.allConceptualEntitySingletonInstanceScope(_descriptionBox_2);
                }
                scope = _allConceptualEntitySingletonInstanceScope_1;
              }
            }
          }
        }
      }
      IScope _xifexpression = null;
      if ((null != scope)) {
        _xifexpression = scope;
      } else {
        _xifexpression = super.getScope(context, reference);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
