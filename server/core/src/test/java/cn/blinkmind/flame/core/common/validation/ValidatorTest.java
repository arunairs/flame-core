package cn.blinkmind.flame.core.common.validation;

import org.testng.annotations.Test;

import static cn.blinkmind.flame.core.common.validation.Matcher.*;
import static cn.blinkmind.flame.core.common.validation.Validator.orElseThrow;
import static cn.blinkmind.flame.core.common.validation.Validator.validateThat;

public class ValidatorTest {

    @Test
    public void pass_the_validation_when_two_objects_are_equivalent_as_expected() {
        validateThat("a", eq("a"), orElseThrow(RuntimeException::new));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throw_exception_when_two_objects_are_not_equivalent_as_expected() {
        validateThat("a", eq("b"), orElseThrow(RuntimeException::new));
    }

    @Test
    public void pass_the_validation_when_1_is_greater_than_0() {
        validateThat(1, gt(0), orElseThrow(RuntimeException::new));
    }

    @Test
    public void pass_the_validation_when_1_is_greater_than_or_equals_0() {
        validateThat(1, ge(0), orElseThrow(RuntimeException::new));
    }
}