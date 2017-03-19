package cn.blinkmind.flame.core.common.validation;

import java.util.function.Supplier;

public class Validator<T> {
    private final T actual;
    private final Matcher<T> matcher;
    private final Runnable otherwise;

    private Validator(T actual, Matcher<T> matcher, Runnable otherwise) {
        this.actual = actual;
        this.matcher = matcher;
        this.otherwise = otherwise;
    }

    public <E> Validator<E> and(final E actual, final Matcher<E> matcher, final Runnable otherwise) {
        return Validator.validateThat(actual, matcher, otherwise);
    }

    public Validator<Boolean> and(final Boolean condition, final Runnable otherwise) {
        return Validator.validateThat(condition, otherwise);
    }

    public static Validator<Boolean> validateThat(final Boolean condition, final Runnable otherwise) {
        return validateThat(condition, Matcher.eq(true), otherwise);
    }

    public static <T> Validator<T> validateThat(final T actual, final Matcher<T> matcher, final Runnable otherwise) {
        Validator<T> validator = new Validator<>(actual, matcher, otherwise);
        validator.match();
        return validator;
    }

    public static Runnable orElseThrow(final Supplier<RuntimeException> supplier) {
        return orElseThrow(supplier.get());
    }

    public static Runnable orElseThrow(final RuntimeException exception) {
        return orElse(() -> {
            throw exception;
        });
    }

    public static Runnable orElse(final Runnable runnable) {
        return runnable;
    }

    private void match() {
        if (!matcher.match(actual)) otherwise.run();
    }
}
