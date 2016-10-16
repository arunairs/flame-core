package cn.blm.promise.server.bean.web;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/10/2016 5:46 PM
 */
public interface ParameterComment<T>
{
	void setComment(T comment);

	T getComment();
}
