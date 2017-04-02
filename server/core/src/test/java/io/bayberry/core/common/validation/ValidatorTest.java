package io.bayberry.core.common.validation;

import org.testng.annotations.Test;

import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

public class ValidatorTest {

    @Test
    public void pass_the_validation_when_two_objects_are_equivalent_as_expected() {
        Validator.validateThat("a", Matcher.eq("a"), Validator.orElseThrow(RuntimeException::new));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throw_exception_when_two_objects_are_not_equivalent_as_expected() {
        Validator.validateThat("a", Matcher.eq("b"), Validator.orElseThrow(RuntimeException::new));
    }

    @Test
    public void pass_the_validation_when_1_is_greater_than_0() {
        Validator.validateThat(1, Matcher.gt(0), Validator.orElseThrow(RuntimeException::new));
    }

    @Test
    public void pass_the_validation_when_1_is_greater_than_or_equals_0() {
        Validator.validateThat(1, Matcher.ge(0), Validator.orElseThrow(RuntimeException::new));
    }
}