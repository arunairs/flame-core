package cn.blinkmind.depot.server.repository.entity;

/**
 * @author jiaan.zhang@outlook.com
 * @date 01/11/2016 11:40 AM
 */
public interface Cleanable
{
	void cleanup(CrudType crudType);
}