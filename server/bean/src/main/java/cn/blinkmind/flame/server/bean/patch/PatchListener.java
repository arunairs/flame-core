package cn.blinkmind.flame.server.bean.patch;

public interface PatchListener
{
    @FunctionalInterface
    interface UpdateCallback<T>
    {
        void callback(PatchEvent.UpdateEvent<T> event);
    }
}
