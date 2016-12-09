package cn.blinkmind.depot.server.bean.patch;

/**
 * @author jiaan.zhang@outlook.com
 * @date 23/11/2016 12:54 AM
 */
public interface ConvertCallback<T>
{
	T converted(T converted);
}
