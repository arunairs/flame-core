package cn.blinkmind.flame.core.common.callback;

public class CallbackFactory {
    public static <T, E> OnSuccessCallback<T, E> onSuccess(T data) {
        return new OnSuccessCallback<>(data);
    }

    public static <T, E> OnErrorCallback<T, E> onError(E error) {
        return new OnErrorCallback<>(error);
    }
}
