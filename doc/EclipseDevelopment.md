# Eclipse Development

There are many strategies for Eclipse development.
The following criteria factored in the choice of Gradle:

1) Automated builds

  Traditional Eclipse development involves lots of tasks performed in the Eclipse IDE.
  Documenting IDE-based development tasks is desirable but notoriously difficult:
  - changes in the IDE make dev. documentation stale very quickly (typically within a year)
  - high labor cost involved in verifying the completeness of the dev. doc
  - interactions among Eclipse features can have surprising consequences on dev. processes
  - Xtext-based development involves a lot of code generation where the combination of
    build dependencies (e.g., dsl.ui depends on dsl) and code generation dependencies (e.g., dsl generates
    code for itself and for dsl.ui) results in circularities that can confuse the Eclipse IDE
    and the developer about whether resources are up to date.
    
  Automated builds should be executable from a unix shell environment without needing an IDE.
  Generated artifacts (e.g., source code) pose a dilema for automation:
  - if generated artifacts are under version control (e.g., GIT), then there is a risk that they may not be
    re-generated because they are present (and possibly with a timestamp that is more recent from their source)
  - if generated artifacts are not under version control, then there is a risk that they may not be 
    generated, which, in the best case scenario, breaks the build; or in the worst case scenario, results
    in a seeminly successful albeit incomplete build where the consequence of the missing generated artifacts
    can be difficult to diagnose when the build is used.
  
2) Simple, reproducible IDE setup

  Experience installing & configuring Eclipse manually via the Eclipse installer or Oomph 
  suggests that this approach should be limited to simple, pre-configured setups.
  
  Automating the configuration & setup of Eclipse is highly preferable as long as it
  does not require a complex Eclipse IDE setup.
  
  Building pre-configured Eclipse installations or images (e.g. docker) with minimal end-user
  setup is preferable.
  
3) Automated build support in IDEs (Eclipse, Inellij)

  Continuous integration executes automated builds in a shell environment.
  Configuring and troubleshooting automated builds with an IDE is particularly helpful.
  Surprisingly, Eclipse isn't necessarily the best IDE for this because of variations across
  Eclipse-based development features for various development concerns.
  
  - Specification of an Eclipse target for development
  
  In the classic Eclipse IDE development, this is done with an Eclipse target.
  However, the Eclipse PDE support for target specification can be very fastidious.
  Although Eclipse provides automation support for Eclipse PDE tasks via Ant, 
  it can be difficult to develop Ant scripts to automate PDE tasks performed at the IDE.
  
## Alternatives considered for automating building of Eclipse plugins, features, P2 repositories & installations

A) [Eclipse PDE](https://www.eclipse.org/pde/)

   Automation via Ant scripts is fragile
   
B) [Eclipse Tycho](https://www.eclipse.org/tycho/)

   This involves using Maven's POM XML as a build configuration/specification/process language.
   Maven-based development is tedious; see for example: https://gradle.org/maven-vs-gradle

C) [Oomph](https://projects.eclipse.org/projects/tools.oomph)

   In principle, this should help; however, the development process is unclear.
   
D) [Eclipse Buildship](https://github.com/eclipse/buildship)

   See: https://github.com/JPL-IMCE/jpl.imce.oml.specification.parent

   - Long build times
   
     The eclipse target is large to build; it could be separated and published somewhere.
   
   - Need to cloak & own a copy of the Eclipse Buildship gradle plugins
   
     See: https://bugs.eclipse.org/bugs/show_bug.cgi?id=469287
   
E) [gradle-bintray-p2-plugin](https://github.com/missedone/gradle-bintray-p2-plugin)

  Built using old Gradle 2.1 APIs
  
F) [bnd-platform](https://github.com/stempler/bnd-platform)

  Built using old Gradle 2.1 APIs

G) [goomph](https://github.com/diffplug/goomph)

  Sounds promising; however, P2-related operations happen behind a layer of Gradle plugins.
  This makes it difficult to understand / troubleshoot the behavior.
  
  Built using old Gradle 2.1 APIs