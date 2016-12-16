package cn.blinkmind.duck.server.repository.entity;

public interface Cleanable
{
	void cleanup(CrudType crudType);
}