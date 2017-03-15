# Ontological Modeling Language core build (Xcore, Xtext, Xtend)

There are no Eclipse metadata files (.classpath, .project, .settings, META-INF/MANIFEST.MF) in GIT:
- Avoiding duplicate information (e.g., dependencies should be specified only once)
- Avoiding platform-specific files (e.g., .classpath)
- Avoiding problems with re-generated files were the contents are syntactically different but logically equivalent

## To clean:

	./gradlew clean cleanEclipse

## Building using Gradle

    ./gradlew build

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
