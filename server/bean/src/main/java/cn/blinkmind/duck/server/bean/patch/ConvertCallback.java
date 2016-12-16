package cn.blinkmind.duck.server.bean.patch;

public interface ConvertCallback<T>
{
	T converted(T converted);
}
