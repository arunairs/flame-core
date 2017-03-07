package cn.blinkmind.flame.core.common.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class Matcher<T> {
    private final T expected;

    protected Matcher() {
        this(null);
    }

    protected Matcher(T expected) {
        this.expected = expected;
    }

    public final boolean match(T actual) {
        return match(actual, expected);
    }

    protected abstract boolean match(T actual, T expected);

    public static <T> Matcher<T> eq(T expected) {
        return new EqualMatcher<>(expected);
    }

    public static <T> Matcher<T> ne(T expected) {
        return new NegativeMatcher<>(new EqualMatcher<>(expected));
    }

    public static <T> Matcher<T> is(Matcher<T> matcher) {
        return new PositiveMatcher<>(matcher);
    }

    public static <T> Matcher<T> not(Matcher<T> matcher) {
        return new NegativeMatcher<>(matcher);
    }

    public static <T extends Number & Comparable<? extends Number>> Matcher<T> gt(T expected) {
        return new GreaterThanMatcher<>(expected);
    }

    public static <T extends Number & Comparable<? extends Number>> Matcher<T> gte(T expected) {
        return new GreaterThanOrEqualMatcher<>(expected);
    }

    public static <T extends Number & Comparable<? extends Number>> Matcher<T> lt(T expected) {
        return new LessThanMatcher<>(expected);
    }

    public static <T extends Number & Comparable<? extends Number>> Matcher<T> lte(T expected) {
        return new LessThanOrEqualMatcher<>(expected);
    }

    public static Matcher<String> blank() {
        return new BlankMatcher();
    }

    public static Matcher<Object> nil() {
        return new NilMatcher<>();
    }

    private static class EqualMatcher<T> extends Matcher<T> {

        public EqualMatcher(T expected) {
            super(expected);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return Objects.equals(actual, expected);
        }
    }

    private abstract static class DelegateMatcher<T> extends Matcher<T> {
        protected Matcher<T> matcher;

        protected DelegateMatcher(Matcher<T> matcher) {
            this.matcher = matcher;
        }
    }

    private static class PositiveMatcher<T> extends DelegateMatcher<T> {

        protected PositiveMatcher(Matcher<T> matcher) {
            super(matcher);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return this.matcher.match(actual, expected);
        }
    }

    private static class NegativeMatcher<T> extends DelegateMatcher<T> {

        protected NegativeMatcher(Matcher<T> matcher) {
            super(matcher);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return !this.matcher.match(actual, expected);
        }
    }

    private static class NilMatcher<T> extends Matcher<T> {

        @Override
        protected boolean match(T actual, T expected) {
            return actual == null;
        }
    }

    private static class BlankMatcher extends Matcher<String> {

        @Override
        protected boolean match(String actual, String expected) {
            return StringUtils.isBlank(actual);
        }
    }

    @SuppressWarnings("unchecked")
    private static class GreaterThanMatcher<T extends Number & Comparable> extends Matcher<T> {
        public GreaterThanMatcher(T expected) {
            super(expected);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return actual.compareTo(expected) > 0;
        }
    }

    @SuppressWarnings("unchecked")
    private static class GreaterThanOrEqualMatcher<T extends Number & Comparable> extends Matcher<T> {

        protected GreaterThanOrEqualMatcher(T expected) {
            super(expected);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return actual.compareTo(expected) >= 0;
        }
    }

    @SuppressWarnings("unchecked")
    private static class LessThanMatcher<T extends Number & Comparable> extends Matcher<T> {
        public LessThanMatcher(T expected) {
            super(expected);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return actual.compareTo(expected) < 0;
        }
    }

    @SuppressWarnings("unchecked")
    private static class LessThanOrEqualMatcher<T extends Number & Comparable> extends Matcher<T> {

        protected LessThanOrEqualMatcher(T expected) {
            super(expected);
        }

        @Override
        protected boolean match(T actual, T expected) {
            return actual.compareTo(expected) <= 0;
        }
    }
}
