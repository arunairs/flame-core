package cn.blinkmind.depot.server.bean.patch;

public interface ConvertCallback<T>
{
	T converted(T converted);
}
