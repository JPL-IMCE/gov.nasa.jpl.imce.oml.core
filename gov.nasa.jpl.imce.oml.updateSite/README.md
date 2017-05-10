# P2 update site for the IMCE Ontological Modeling Language

## Workbench usage

- Install the OML Workbench on top of Eclipse Neon.3 with Xtext 2.11 & CDO (see below).
- Create a new project.
- The OML Xtext-based editor & outline is associated with '*.oml' files. Note that you may be prompted whether to convert the project to an Xtext project. Click OK.

## API-based usage

- EMF-based technologies can be used to develop applications that query, transform, generate OML models.
   
- Example of a standalone EMF-based application for processing OML and Eclipse UML resources:
  
  ```scala
  import org.eclipse.emf.common.util.URI
  import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup
  import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil
  import org.eclipse.xtext.resource.XtextResourceSet


  import gov.nasa.jpl.imce.oml.dsl.OntologicalModelingLanguageStandaloneSetup
  import gov.nasa.jpl.imce.oml.model.common.Extent
  import gov.nasa.jpl.imce.oml.model.common.CommonFactory

  import scala.{Array,Unit}
  import scala.Predef.String

  object Example {
  
    def main(args: Array[String]): Unit = {
      // Ensure the EMF-based metamodels are properly registered     
      XcoreStandaloneSetup.doSetup()
      OntologicalModelingLanguageStandaloneSetup.doSetup()
    
      val rs = new XtextResourceSet()
      // If needed, initialize other EMF-based metamodels
      UMLResourcesUtil.initLocalRegistries(rs)
    
      // use rs to load, create, transform, query, serialize EMF-based resources including OML
    
      // create an OML resource
      val omlFile: String = "example.oml"
      val omlR = xs.createResource(URI.createFileURI(omlFile))
      
      // the root of an OML resource is an OML Extent
      val omlE = CommonFactory.eINSTANCE.createExtent()
      // construct additional OML elements
      
      // save the constructed OML resource in concrete syntax
      omlR.save(null)
    }
  }
  ```

## Installation

Follow the procedure to install Eclipse Neon.3 with Xtext 2.11 & CDO:

- Install Eclipse Neon.3 from: https://www.eclipse.org/downloads/packages/eclipse-ide-eclipse-committers/neon3
   
   If you have installed Eclipse before or elsewhere, there will probably be an "Import wizard"
   to import previously-installed features.
   
   Click Cancel.
   
   Also note that it is highly recommended to start with a fresh install.
   
- Run Eclipse Neon.3 and select Help > Install New Software...
   
- Add the Xtext update site: http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/
- Install 
    - Modeling / CDO Model Repository SDK 4.5.0
    - Modeling / Ecore Diagram ditor (SDK) 3.2.1
    - Xtext / Xtext Complete SDK 2.11 
    - Xtext / Xtend IDE 2.11
    
   Note that if you are using the Eclipse Neon.3 Modeling package, you may be prompted that the Xtext Complete SDK and Xtend IE cannot be installed without removing EMF Parsley first. This is reportedly offered as a calculated alternative installation option. It is recommended to accept this option.

- In Eclipse, add an update site: https://dl.bintray.com/jpl-imce/gov.nasa.jpl.imce/gov.nasa.jpl.imce.oml.updateSite/[version]/
   
  Replace [version] as appropriate.

- In Eclipse, install all features from the above update site

## Development workflow

1) Generate the descriptor [site.xml](site.xml):

```shell
./gradlew :gov.nasa.jpl.imce.oml.updateSite:eclipseSite
```

2) In the Eclipse IDE, build the update site

   Note that all the Eclipse plugin projects have in their build.properties directives to include source directories despite warnings from Eclipse PDE UI.
   Eclipse PDE UI warns that such source directories are included in source builds.
   This is true only for 100%-based java sources; it is not true for other source content (e.g., *.xcore, *.xtend). 
  
   
3) Publish

```shell
export BINTRAY_API_KEY "<bintray api key>"
export BINTRAY_USER "<bintray username>"
./gradlew :gov.nasa.jpl.imce.oml.updateSite:bintrayUpload
```
