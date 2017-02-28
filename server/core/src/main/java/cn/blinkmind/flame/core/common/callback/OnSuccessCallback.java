package cn.blinkmind.flame.core.common.callback;

import java.util.function.Supplier;

public class OnSuccessCallback<T, E> implements Callback<T, E> {
    private T data;

    public OnSuccessCallback(T data) {
        this.data = data;
    }

    @Override
    public <R> CallbackStream<T, R, E> map(Supplier<R> supplier) {
        return new OnSuccessStream<>(supplier, this.data);
    }
}
