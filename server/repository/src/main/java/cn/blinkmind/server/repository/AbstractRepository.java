package cn.blinkmind.server.repository;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:45 PM
 */
public abstract class AbstractRepository<T, ID extends Serializable> implements CrudRepository<T, ID>
{
	@Override
	public <S extends T> S save(S entity)
	{
		return null;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities)
	{
		return null;
	}

	@Override
	public T findOne(ID id)
	{
		return null;
	}

	@Override
	public boolean exists(ID id)
	{
		return false;
	}

	@Override
	public Iterable<T> findAll()
	{
		return null;
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids)
	{
		return null;
	}

	@Override
	public long count()
	{
		return 0;
	}

	@Override
	public void delete(ID id)
	{

	}

	@Override
	public void delete(T entity)
	{

	}

	@Override
	public void delete(Iterable<? extends T> entities)
	{

	}

	@Override
	public void deleteAll()
	{

	}
}
