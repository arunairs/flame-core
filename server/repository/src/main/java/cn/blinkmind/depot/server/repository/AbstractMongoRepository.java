package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.exception.ResourceNotFoundException;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author jiaan.zhang@oracle.com
 * @date 01/10/2016 1:53 PM
 */
public abstract class AbstractMongoRepository<T, ID extends Serializable> implements Repository
{
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected abstract Class<T> getEntityClass();

	public T save(final T entity)
	{
		mongoTemplate.save(entity);
		return entity;
	}

	public List<T> save(final Iterable<T> entites)
	{
		for (T entity : entites)
			save(entity);
		return (List<T>) entites;
	}

	public T update(final T entity)
	{
		return save(entity);
	}

	public T update(final Query query, final T entity)
	{
		DBObject object = (DBObject) mongoTemplate.getConverter().convertToMongoType(entity);
		Update update = Update.fromDBObject(object);
		return mongoTemplate.findAndModify(query, update, getEntityClass());
	}

	public T require(final ID id)
	{
		T entity = get(id);
		if (entity == null) throw new ResourceNotFoundException();
		return entity;
	}

	public T get(final ID id)
	{
		return mongoTemplate.findById(id, getEntityClass());
	}

	public T findOne(final Query query)
	{
		return mongoTemplate.findOne(query, getEntityClass());
	}

	public boolean exists(final ID id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where(ID).is(id));
		return mongoTemplate.exists(query, getEntityClass());
	}

	public boolean exists(final Query query)
	{
		return mongoTemplate.exists(query, getEntityClass());
	}

	public List<T> findAll(final Query query)
	{
		return mongoTemplate.find(query, getEntityClass());
	}

	public List<T> findAll()
	{
		return mongoTemplate.findAll(getEntityClass());
	}

	public List<T> findAll(final Iterable<ID> ids)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where(ID).in(ids));
		return mongoTemplate.find(query, getEntityClass());
	}

	public T insert(final T entity)
	{
		mongoTemplate.insert(entity);
		return entity;
	}

	public List<T> insert(final Iterable<T> entities)
	{
		for (T entity : entities)
			insert(entity);
		return (List<T>) entities;
	}

	public void insertAll(final Collection<? extends Object> objectsToSave)
	{
		if (objectsToSave == null || objectsToSave.size() < 1) return;
		mongoTemplate.insertAll(objectsToSave);
	}

	public long count(final Query query)
	{
		return mongoTemplate.count(query, getEntityClass());
	}

	public void delete(final ID id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, getEntityClass());
	}

	public void delete(final T entity)
	{
		mongoTemplate.remove(entity);
	}

	public void delete(final Query query)
	{
		mongoTemplate.remove(query, getEntityClass());
	}

	public void delete(final Iterable<T> entities)
	{
		for (T entity : entities)
			delete(entity);
	}

	public BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode)
	{
		return mongoTemplate.bulkOps(bulkMode, getEntityClass());
	}
}
