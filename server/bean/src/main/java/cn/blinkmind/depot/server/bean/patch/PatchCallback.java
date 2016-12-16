package cn.blinkmind.depot.server.bean.patch;

public interface PatchCallback<T, E>
{
	void updated(T previous, T current, E source);
}
