package de.wps.ddd.banking;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.RECORDS;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.containAnyMethodsThat;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaConstructorCall;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.lang.reflect.Modifier;

@AnalyzeClasses(packagesOf = ValueObjectArchTest.class, importOptions = { ImportOption.DoNotIncludeTests.class })
class ValueObjectArchTest {

    /**
     * This rule checks, that constructors of records with factory methods are not called from any other class.
     */
    @ArchTest
    final ArchRule factoryMethodsForValueObjectsMustBeUsed = classes()
            .that(areRecordsWithFactoryMethod())
            .should(callConstructorOnlyFromTheSameClass())
            .as("Factory methods of record based value objects must be used");

    @ArchTest
    final ArchRule boundedContexts = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage(ValueObjectArchTest.class.getPackageName() + "..")
            .as("Bounded contexts")
            .layer("accounting").definedBy("..accounting..")
            .layer("credit").definedBy("..credit..")
            .layer("sharedKernel").definedBy("..sharedKernel..")
            .whereLayer("accounting").mayNotBeAccessedByAnyLayer()
            .whereLayer("credit").mayOnlyBeAccessedByLayers("accounting")
            .whereLayer("sharedKernel").mayOnlyBeAccessedByLayers("accounting", "credit")
            .ensureAllClassesAreContainedInArchitecture();

    /**
     * @return predicate matching Records with a factory method.
     * @see #staticFactoryMethod()
     */
    private static DescribedPredicate<JavaClass> areRecordsWithFactoryMethod() {
        return RECORDS.and(containAnyMethodsThat(staticFactoryMethod()));
    }

    /**
     * Static factory method means a static method returning the class in which it is declared
     *
     * @return predicate testing for staticFactoryMethods
     */
    private static DescribedPredicate<JavaMethod> staticFactoryMethod() {
        return new DescribedPredicate<>("static factory method") {
            @Override
            public boolean test(JavaMethod javaMethod) {
                return javaMethod.getOwner().equals(javaMethod.getReturnType())
                        && (javaMethod.reflect().getModifiers() & Modifier.STATIC) > 0;
            }
        };
    }

    private static ArchCondition<JavaClass> callConstructorOnlyFromTheSameClass() {
        return new ArchCondition<>("call constructor only from the same class") {
            @Override
            public void check(JavaClass javaConstructor, ConditionEvents events) {
                for (JavaConstructorCall call : javaConstructor.getConstructorCallsToSelf()) {
                    boolean originIsTarget = call.getOriginOwner().equals(call.getTargetOwner());
                    events.add(new SimpleConditionEvent(call, originIsTarget, call.getDescription()));
                }
            }
        };
    }
}
