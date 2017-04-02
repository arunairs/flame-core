package io.bayberry.core.common.patch;

public interface PatchListener
{
    @FunctionalInterface
    interface UpdateCallback<T>
    {
        void callback(PatchEvent.UpdateEvent<T> event);
    }
}
