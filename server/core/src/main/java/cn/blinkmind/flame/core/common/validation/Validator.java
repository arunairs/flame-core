package cn.blinkmind.flame.core.common.validation;

public class Validator<T> {
    private T actual;
    private Matcher<T> matcher;
    private Runnable otherwise;

    private Validator(T actual, Matcher<T> matcher, Runnable otherwise) {
        this.actual = actual;
        this.matcher = matcher;
        this.otherwise = otherwise;
    }

    public <E> Validator<E> and(E actual, Matcher<E> matcher, Runnable otherwise) {
        return Validator.validateThat(actual, matcher, otherwise);
    }

    public Validator<Boolean> and(Boolean condition, Runnable otherwise) {
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

    private void match() {
        if (!matcher.match(actual)) otherwise.run();
    }
}
