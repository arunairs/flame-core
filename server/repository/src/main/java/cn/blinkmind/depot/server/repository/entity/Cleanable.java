package cn.blinkmind.depot.server.repository.entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 01/11/2016 11:40 AM
 */
public interface Cleanable
{
	void cleanup(CRUD crud);
}