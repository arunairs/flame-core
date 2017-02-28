package cn.blinkmind.flame.core.common.callback;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class OnSuccessStream<T, R, E> implements CallbackStream<T, R, E> {
    private T data;
    private R output;

    public OnSuccessStream(Supplier<R> supplier, T data) {
        this.data = data;
        this.output = supplier.get();
    }

    @Override
    public CallbackStream<T, R, E> onSuccess(BiConsumer<T, R> consumer) {
        consumer.accept(this.data, this.output);
        return this;
    }

    @Override
    public R collect() {
        return this.output;
    }
}
