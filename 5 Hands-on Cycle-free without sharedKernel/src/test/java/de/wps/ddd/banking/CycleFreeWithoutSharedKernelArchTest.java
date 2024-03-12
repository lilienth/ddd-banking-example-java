package de.wps.ddd.banking;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = CycleFreeWithoutSharedKernelArchTest.class, importOptions = { ImportOption.DoNotIncludeTests.class })
public class CycleFreeWithoutSharedKernelArchTest {

    @ArchTest
    final ArchRule boundedContexts = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage(CycleFreeWithoutSharedKernelArchTest.class.getPackageName() + "..")
            .as("Bounded contexts")
            .layer("accounting").definedBy("..accounting..")
            .layer("credit").definedBy("..credit..")
            .whereLayer("accounting").mayNotBeAccessedByAnyLayer()
            .whereLayer("credit").mayOnlyBeAccessedByLayers("accounting")
            .ensureAllClassesAreContainedInArchitecture();
}
