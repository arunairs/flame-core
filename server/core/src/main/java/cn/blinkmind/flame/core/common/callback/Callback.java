package cn.blinkmind.flame.core.common.callback;

import java.util.function.Supplier;

public interface Callback<T, E> {
    <R> CallbackStream<T, R, E> map(Supplier<R> supplier);
}
