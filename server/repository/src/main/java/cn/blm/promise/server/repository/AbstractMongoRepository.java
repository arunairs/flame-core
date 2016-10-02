package cn.blm.promise.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * @author jiaan.zhang@oracle.com
 * @date 01/10/2016 1:53 PM
 */
public abstract class AbstractMongoRepository<T, ID extends Serializable>
{
	@Autowired
	protected MongoTemplate mongoTemplate;

	private Class<T> entityClass;

	protected abstract Class<T> getEntityClass();

	@PostConstruct
	private void init()
	{
		this.entityClass = getEntityClass();
	}

	public <S extends T> S save(S entity)
	{
		mongoTemplate.save(entity);
		return entity;
	}

	public <S extends T> List<S> save(Iterable<S> entites)
	{
		for (S entity : entites)
			save(entity);
		return (List<S>) entites;
	}

	public T findOne(ID id)
	{
		return findOne(id, entityClass);
	}

	private T findOne(ID id, Class<T> entityClass)
	{
		return mongoTemplate.findById(id, entityClass);
	}

	public boolean exists(ID id)
	{
		return exists(id, entityClass);
	}

	private boolean exists(ID id, Class<T> entityClass)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.exists(query, entityClass);
	}

	public List<T> findAll()
	{
		return mongoTemplate.findAll(entityClass);
	}

	public List<T> findAll(Sort sort)
	{
		return null;
	}

	public Page<T> findAll(Pageable pageable)
	{
		return null;
	}

	public <S extends T> S insert(S entity)
	{
		mongoTemplate.insert(entity);
		return entity;
	}

	public <S extends T> List<S> insert(Iterable<S> entities)
	{
		for (S entity : entities)
			insert(entity);
		return (List<S>) entities;
	}

	public Iterable<T> findAll(Iterable<ID> ids)
	{
		return null;
	}

	public long count()
	{
		return count(entityClass);
	}

	private long count(Class<T> entityClass)
	{
		return mongoTemplate.count(null, entityClass);
	}

	public void delete(ID id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, entityClass);
	}

	public void delete(T entity)
	{
		mongoTemplate.remove(entity);
	}

	public <S extends T> void delete(Iterable<S> entities)
	{
		for (S entity : entities)
			delete(entity);
	}

	public void deleteAll()
	{
		mongoTemplate.dropCollection(entityClass);
	}
}
