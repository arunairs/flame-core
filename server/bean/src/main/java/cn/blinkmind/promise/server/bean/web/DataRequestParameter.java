package cn.blinkmind.promise.server.bean.web;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/10/2016 5:38 PM
 */
public interface DataRequestParameter<T> extends RequestParameter
{
	void setData(T data);

	T getData();
}
