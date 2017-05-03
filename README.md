# Ontological Modeling Language core build (Xcore, Xtext, Xtend)

There are no Eclipse metadata files (.classpath, .project, .settings, META-INF/MANIFEST.MF) in GIT:
- Avoiding duplicate information (e.g., dependencies should be specified only once)
- Avoiding platform-specific files (e.g., .classpath)
- Avoiding problems with re-generated files were the contents are syntactically different but logically equivalent

## Eclipse (everyone)

- Install Eclipse Neon.3 from: https://www.eclipse.org/downloads/packages/eclipse-ide-eclipse-committers/neon3
   
   If you have installed Eclipse before or elsewhere, there will probably be an "Import wizard"
   to import previously-installed features.
   
   Click Cancel.
   
- Add the Xtext update site: http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/
- Install 
    - Modeling / CDO Model Repository SDK 4.5.0
    - Modeling / Ecore Diagram ditor (SDK) 3.2.1
    - Xtext / Xtext Complete SDK 2.11 
    - Xtext / Xtend IDE 2.11

## Eclipse (Developers)

- Add the Scala IDE update site: http://download.scala-ide.org/sdk/lithium/e46/scala212/dev/site
- Install everything from the Scala IDE
- Install Scala 2.11 (on mac: brew install scala@2.11)
- ln -s /usr/local/Cellar/scala@2.11/2.11.8_1 scala2.11
- In Eclipse preferences, set the location for Scala 2.11
- In Eclipse preferences, set a Java classpath variable: GRADLE_HOME = ~/.gradle
- Use gradle to build all the projects & generate the Eclipse project metadata needed for import into Eclipse


## To clean:

	./gradlew clean cleanEclipse

## Building using Gradle

    ./gradlew build
    
Sometimes, `build` fails because `:gov.nasa.jpl.imce.oml.model:generateXtext` fails to resolve the *.xcore files.
The following usually suffices to avoid this problem:

    ./gradlew :gov.nasa.jpl.imce.oml.model:generateXtext
    ./gradlew build

## Gradle task dependencies

    ./gradlew :<subproject>:<task> :<subproject>:taskTree
    
## To import in Intellij:

1) `New > Project from existing sources...`

2) Choose the `gradle` import model

3) Select the option: `Use default gradle wrapper (recommended)`

Note that this project's gradle configuration does not include support for working on the scala sub-projects:
- [gov.nasa.jpl.imce.oml.tables](gov.nasa.jpl.imce.oml.tables)
- [gov.nasa.jpl.imce.oml.resolver](gov.nasa.jpl.imce.oml.resolver)

To work on these, import these sub-projects as separate Intellij SBT projects.

## To import in eclipse:

1) Build everything with gradle

     ```
     ./gradlew build
     ```
     
2) Generate Eclipse metadata

     ```
     ./gradlew eclipse
     ```
     
3) Define a `GRADLE_HOME` Eclipse Classpath variable

   This variable can be defined in Eclipse' preferences in `Java/Build Path/Classpath Variables`
   The value of this variable should be the folder corresponding to `$HOME/.gradle`
   
4) Import the Eclipse projects from this directory

Note that this project's gradle configuration does not include support for working on the scala sub-projects:
- [gov.nasa.jpl.imce.oml.tables](gov.nasa.jpl.imce.oml.tables)
- [gov.nasa.jpl.imce.oml.resolver](gov.nasa.jpl.imce.oml.resolver)

Working on these Sacla sub-projects is easier with Intellij than with Eclipse 
because Intellij uses the SBT project configuration to configure Intellij.

In contrast, the Scala-Eclipse IDE does not yet use the SBT project configuration to configure Eclipse;
which means that Eclipse needs to be configured somehow (manually or by generation). The `sbt eclipse`
command generates useful Eclipse configuration metadata (.project, .classpath, ...); however, this configuration
typically needs to be further adjusted manually for Scala development in Eclipse.

## Building using Eclipse:

- Execute the procedure to `import in Eclipse`
- The projects can be cleaned & rebuilt in the Eclipse IDE

## Gradle

See [gradle/wrapper/gradle-wrapper.properties](gradle/wrapper/gradle-wrapper.properties)

The gradle distribution is based on the gradle-script-kotlin integration.
See the development builds available here: 
https://repo.gradle.org/gradle/webapp/#/artifacts/browse/tree/General/dist-snapshots

For release notes:
https://github.com/gradle/gradle-script-kotlin/releases

