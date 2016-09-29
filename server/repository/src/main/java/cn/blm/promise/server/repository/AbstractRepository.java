package cn.blm.promise.server.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:45 PM
 */
public abstract class AbstractRepository<T, ID extends Serializable> implements JpaRepository<T, ID>
{
	@Override
	public List<T> findAll()
	{
		return null;
	}

	@Override
	public List<T> findAll(Sort sort)
	{
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable)
	{
		return null;
	}

	@Override
	public List<T> findAll(Iterable<ID> ids)
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

	@Override
	public <S extends T> S save(S entity)
	{
		return null;
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities)
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
	public void flush()
	{

	}

	@Override
	public <S extends T> S saveAndFlush(S entity)
	{
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<T> entities)
	{

	}

	@Override
	public void deleteAllInBatch()
	{

	}

	@Override
	public T getOne(ID id)
	{
		return null;
	}

	@Override
	public <S extends T> S findOne(Example<S> example)
	{
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example)
	{
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort)
	{
		return null;
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable)
	{
		return null;
	}

	@Override
	public <S extends T> long count(Example<S> example)
	{
		return 0;
	}

	@Override
	public <S extends T> boolean exists(Example<S> example)
	{
		return false;
	}
}
