package cn.blinkmind.flame.server.util.patch;

public interface PatchListener
{
    @FunctionalInterface
    interface UpdateCallback<T>
    {
        void callback(PatchEvent.UpdateEvent<T> event);
    }
}
