package cn.blinkmind.flame.core.common.validation;

import org.testng.annotations.Test;

import static cn.blinkmind.flame.core.common.validation.Matcher.eq;
import static cn.blinkmind.flame.core.common.validation.Validator.validateThat;

public class ValidatorTest {

    @Test
    public void pass_the_validation_when_two_objects_are_equivalent_as_expected() {
        String first = "test";
        String second = "test";

        validateThat(first, eq(second), () -> {
            throw new RuntimeException();
        });
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throw_exception_when_two_objects_are_not_equivalent_as_expected() {
        String first = "first string";
        String second = "second string";

        validateThat(first, eq(second), () -> {
            throw new RuntimeException();
        });
    }

}