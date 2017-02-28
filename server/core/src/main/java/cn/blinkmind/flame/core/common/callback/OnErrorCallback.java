package cn.blinkmind.flame.core.common.callback;

import java.util.function.Supplier;

public class OnErrorCallback<T, E> implements Callback<T, E> {
    private E error;

    public OnErrorCallback(E error) {
        this.error = error;
    }

    @Override
    public <R> CallbackStream<T, R, E> map(Supplier<R> supplier) {
        return new OnErrorStream<>(supplier, error);
    }
}
