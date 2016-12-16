package cn.blinkmind.duck.server.bean.patch;

public interface PatchCallback<T, E>
{
	void updated(T previous, T current, E source);
}
