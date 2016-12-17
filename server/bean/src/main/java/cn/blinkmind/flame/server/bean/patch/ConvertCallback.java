package cn.blinkmind.flame.server.bean.patch;

public interface ConvertCallback<T>
{
	T converted(T converted);
}
