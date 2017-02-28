package cn.blinkmind.flame.core.common.callback;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class OnErrorStream<T, R, E> implements CallbackStream<T, R, E> {
    private E error;
    private R output;

    public OnErrorStream(Supplier<R> supplier, E error) {
        this.error = error;
        this.output = supplier.get();
    }

    @Override
    public CallbackStream<T, R, E> onError(BiConsumer<E, R> consumer) {
        consumer.accept(this.error, this.output);
        return this;
    }

    @Override
    public R collect() {
        return this.output;
    }
}
