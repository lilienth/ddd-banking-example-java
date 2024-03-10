package de.wps.ddd.banking;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.jmolecules.archunit.JMoleculesDddRules;

@AnalyzeClasses(packagesOf = RichDomainModelArchTest.class, importOptions = { ImportOption.DoNotIncludeTests.class })
public class RichDomainModelArchTest {
    @ArchTest
    final ArchRule dddRules = JMoleculesDddRules.all();

    @ArchTest
    final ArchRule boundedContexts = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage(RichDomainModelArchTest.class.getPackageName() + "..")
            .as("Bounded contexts")
            .layer("accounting").definedBy("..accounting..")
            .layer("credit").definedBy("..credit..")
            .layer("sharedKernel").definedBy("..sharedKernel..")
            .whereLayer("accounting").mayNotBeAccessedByAnyLayer()
            .whereLayer("credit").mayOnlyBeAccessedByLayers("accounting")
            .whereLayer("sharedKernel").mayOnlyBeAccessedByLayers("accounting", "credit")
            .ensureAllClassesAreContainedInArchitecture();
}
