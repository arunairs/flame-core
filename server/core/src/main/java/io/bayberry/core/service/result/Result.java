package io.bayberry.core.service.result;

import java.util.function.Consumer;

public class Result<T, E> {

    private T value;
    private E error;

    private Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <T, E> Result<T, E> ok(T value) {
        return new Result<>(value, null);
    }

    public static <T, E> Result<T, E> fail(E error) {
        return new Result<>(null, error);
    }

    public static <T, E> Result<T, E> failIfNull(T value, E error) {
        return value == null ? fail(error) : ok(value);
    }

    public T getValue() {
        return this.value;
    }

    public E getError() {
        return this.error;
    }

    public boolean hasError() {
        return this.error != null;
    }

    public T orElse(Consumer<E> consumer) {
        if (this.value == null)
            consumer.accept(error);
        return this.value;
    }

    public T orElse(Runnable runnable) {
        if (this.value == null)
            runnable.run();
        return this.value;
    }
}
