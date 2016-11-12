package cn.blinkmind.promise.server.repository;

import cn.blinkmind.promise.server.repository.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author jiaan.zhang@oracle.com
 * @date 01/10/2016 1:53 PM
 */
public abstract class AbstractMongoRepository<T, ID extends Serializable>
{
	private static final String FIELD_ID = "_id";

	@Autowired
	protected MongoTemplate mongoTemplate;

	private Class<T> entityClass;

	protected abstract Class<T> getEntityClass();

	@PostConstruct
	private void init()
	{
		this.entityClass = getEntityClass();
	}

	public <S extends T> S save(final S entity)
	{
		mongoTemplate.save(entity);
		return entity;
	}

	public <S extends T> List<S> save(final Iterable<S> entites)
	{
		for (S entity : entites)
			save(entity);
		return (List<S>) entites;
	}

	public T require(final ID id)
	{
		return require(id, entityClass);
	}

	private T require(final ID id, final Class<T> entityClass)
	{
		T entity = findOne(id, entityClass);
		if (entity == null) throw new ResourceNotFoundException();
		return entity;
	}

	public T findOne(final ID id)
	{
		return findOne(id, entityClass);
	}

	private T findOne(final ID id, final Class<T> entityClass)
	{
		return mongoTemplate.findById(id, entityClass);
	}

	public boolean exists(final ID id)
	{
		return exists(id, entityClass);
	}

	private boolean exists(final ID id, final Class<T> entityClass)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where(FIELD_ID).is(id));
		return mongoTemplate.exists(query, entityClass);
	}

	public List<T> findAll(final Query query, final Class<T> entityClass)
	{
		return mongoTemplate.find(query, entityClass);
	}

	public List<T> findAll()
	{
		return mongoTemplate.findAll(entityClass);
	}

	public List<T> findIn(final Collection<?> collection)
	{
		return findIn(collection, entityClass);
	}

	private List<T> findIn(final Collection<?> collection, final Class<T> entityClass)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where(FIELD_ID).in(collection));
		return mongoTemplate.find(query, entityClass);
	}

	public <S extends T> S insert(final S entity)
	{
		mongoTemplate.insert(entity);
		return entity;
	}

	public <S extends T> List<S> insert(final Iterable<S> entities)
	{
		for (S entity : entities)
			insert(entity);
		return (List<S>) entities;
	}

	public void insertAll(final Collection<? extends Object> objectsToSave)
	{
		if (objectsToSave == null || objectsToSave.size() < 1) return;
		mongoTemplate.insertAll(objectsToSave);
	}

	public Iterable<T> findAll(final Iterable<ID> ids)
	{
		return null;
	}

	public long count()
	{
		return count(entityClass);
	}

	private long count(final Class<T> entityClass)
	{
		return mongoTemplate.count(null, entityClass);
	}

	public void delete(final ID id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, entityClass);
	}

	public void delete(final T entity)
	{
		mongoTemplate.remove(entity);
	}

	public void delete(final Query query)
	{
		mongoTemplate.remove(query, entityClass);
	}

	private void delete(final Query query, final Class<T> entityClass)
	{
		mongoTemplate.remove(query, entityClass);
	}

	public <S extends T> void delete(final Iterable<S> entities)
	{
		for (S entity : entities)
			delete(entity);
	}

	public BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode)
	{
		return mongoTemplate.bulkOps(bulkMode, entityClass);
	}

	private BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode, final Class<T> entityClass)
	{
		return mongoTemplate.bulkOps(bulkMode, entityClass);
	}
}
