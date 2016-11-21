package cn.blinkmind.promise.server.bean.patch;

/**
 * @author jiaan.zhang@oracle.com
 * @date 21/11/2016 12:31 AM
 */
public interface PatchCallback<T, E>
{
	void updated(T previous, T current, E source);
}
