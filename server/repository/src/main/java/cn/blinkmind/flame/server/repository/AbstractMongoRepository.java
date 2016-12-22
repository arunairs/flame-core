package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.EntityBean;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

public abstract class AbstractMongoRepository<T extends EntityBean, ID extends Serializable> implements Repository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    protected MongoTemplate getMongoTemplate()
    {
        return mongoTemplate;
    }

    protected abstract Class<T> getEntityClass();

    public T update(final T entity)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(entity.getId()));
        doBeforeUpdate(entity);
        return update(query, entity);
    }

    public T update(final Query query, final T entity)
    {
        DBObject object = (DBObject) getMongoTemplate().getConverter().convertToMongoType(entity);
        Update update = Update.fromDBObject(object);
        doBeforeUpdate(entity);
        return getMongoTemplate().findAndModify(query, update, options().upsert(false), getEntityClass());
    }

    public T update(final Query query, final Update update)
    {
        doBeforeUpdate(update);
        return getMongoTemplate().findAndModify(query, update, options().upsert(false), getEntityClass());
    }

    public T require(final ID id)
    {
        T entity = get(id);
        if (entity == null) throw new ResourceNotFoundException();
        return entity;
    }

    public T get(final ID id)
    {
        return getMongoTemplate().findById(id, getEntityClass());
    }

    public T findOne(final Query query)
    {
        return getMongoTemplate().findOne(query, getEntityClass());
    }

    public boolean exists(final ID id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        return getMongoTemplate().exists(query, getEntityClass());
    }

    public boolean exists(final Query query)
    {
        return getMongoTemplate().exists(query, getEntityClass());
    }

    public List<T> findAll(final Query query)
    {
        return getMongoTemplate().find(query, getEntityClass());
    }

    public List<T> findAll()
    {
        return getMongoTemplate().findAll(getEntityClass());
    }

    public List<T> findAll(final Iterable<ID> ids)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).in(ids));
        return getMongoTemplate().find(query, getEntityClass());
    }

    public T insert(final T entity)
    {
        doBeforeInsert(entity);
        getMongoTemplate().insert(entity);
        return entity;
    }

    public List<T> insert(final Iterable<T> entities)
    {
        for (T entity : entities)
        {
            insert(entity);
        }
        return (List<T>) entities;
    }

    public void insertAll(final Collection<? extends T> objectsToSave)
    {
        if (objectsToSave == null || objectsToSave.size() < 1) return;
        getMongoTemplate().insertAll(objectsToSave);
    }

    public long count(final Query query)
    {
        return getMongoTemplate().count(query, getEntityClass());
    }

    public void delete(final ID id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        getMongoTemplate().remove(query, getEntityClass());
    }

    public void delete(final T entity)
    {
        getMongoTemplate().remove(entity);
    }

    public void delete(final Query query)
    {
        getMongoTemplate().remove(query, getEntityClass());
    }

    public void delete(final Iterable<T> entities)
    {
        for (T entity : entities)
        {
            delete(entity);
        }
    }

    public BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode)
    {
        return getMongoTemplate().bulkOps(bulkMode, getEntityClass());
    }

    private void doBeforeInsert(final T entity)
    {
        if (entity.getCreatedDate() != null)
            entity.refreshCreatedDate();
    }

    private void doBeforeInsert(final Update update)
    {
        update.set("createdDate", new Date());
    }

    private void doBeforeUpdate(final T entity)
    {
        entity.refreshUpdatedDate();
    }

    private void doBeforeUpdate(final Update update)
    {
        update.set("updatedDate", new Date());
    }
}
