package cn.blinkmind.flame.server.repository.entity;

public interface Cleanable
{
	void cleanup(CrudType crudType);
}