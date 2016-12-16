package cn.blinkmind.depot.server.repository.entity;

public interface Cleanable
{
	void cleanup(CrudType crudType);
}