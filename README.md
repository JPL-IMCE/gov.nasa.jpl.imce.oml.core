# Ontological Modeling Language core build (Xcore, Xtext, Xtend)

There are no Eclipse metadata files (.classpath, .project, .settings, META-INF/MANIFEST.MF) in GIT:
- Avoiding duplicate information (e.g., dependencies should be specified only once)
- Avoiding platform-specific files (e.g., .classpath)
- Avoiding problems with re-generated files were the contents are syntactically different but logically equivalent

## To clean:

	./gradlew clean cleanEclipse

## Building using Gradle

    ./gradlew build

## To import in eclipse:

1) Build everything with gradle

     ```
     ./gradlew build
     ```
     
2) Generate Eclipse metadata

     ```
     ./gradlew eclipse
     ```
     
3) Import the projects in Eclipse

## Building using Eclipse:

- Execute the procedure to `import in Eclipse`
- The projects can be cleaned & rebuilt in the Eclipse IDE
