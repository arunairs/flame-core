package cn.blinkmind.flame.core.common.callback;

import java.util.function.BiConsumer;

public interface CallbackStream<T, R, E> {
    default CallbackStream<T, R, E> onSuccess(BiConsumer<T, R> consumer) {
        return this;
    }

    default CallbackStream<T, R, E> onError(BiConsumer<E, R> consumer) {
        return this;
    }

    R collect();
}
