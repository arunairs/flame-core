package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.Persistable;
import cn.blinkmind.flame.server.repository.event.AfterEntityCreatedEvent;
import cn.blinkmind.flame.server.repository.event.AfterEntityUpdatedEvent;
import cn.blinkmind.flame.server.repository.event.AfterUpdateAppliedEvent;
import cn.blinkmind.flame.server.repository.event.BeforeEntityCreatedEvent;
import cn.blinkmind.flame.server.repository.event.BeforeEntityUpdatedEvent;
import cn.blinkmind.flame.server.repository.event.BeforeUpdateAppliedEvent;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import cn.blinkmind.flame.server.repository.query.Keys;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

public abstract class AbstractMongoRepository<T extends Persistable<ID>, ID extends Serializable>
{
    @Autowired
    private ApplicationEventPublisher publisher;

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
        query.addCriteria(Criteria.where(Keys.ID).is(entity.getId()));
        return update(query, entity);
    }

    public T update(final Query query, final T entity)
    {
        this.publisher.publishEvent(new BeforeEntityUpdatedEvent<>(entity));
        DBObject object = (DBObject) getMongoTemplate().getConverter().convertToMongoType(entity);
        Update update = Update.fromDBObject(object);
        T result = update(query, update);
        this.publisher.publishEvent(new AfterEntityUpdatedEvent<>(entity));
        return result;
    }

    public T update(final Query query, final Update update)
    {
        this.publisher.publishEvent(new BeforeUpdateAppliedEvent(update));
        T result = getMongoTemplate().findAndModify(query, update, options().upsert(false).returnNew(true), getEntityClass());
        this.publisher.publishEvent(new AfterUpdateAppliedEvent(update));
        return result;
    }

    public T require(final ID id)
    {
        T entity = get(id);
        if (entity == null) throw new ResourceNotFoundException();
        return entity;
    }

    public T get(final ID id)
    {
        return id == null ? null : getMongoTemplate().findById(id, getEntityClass());
    }

    public T get(final T entity)
    {
        return entity == null ? null : get(entity.getId());
    }


    public T findOne(final Query query)
    {
        return getMongoTemplate().findOne(query, getEntityClass());
    }

    public boolean exists(final ID id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(id));
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
        query.addCriteria(Criteria.where(Keys.ID).in(ids));
        return getMongoTemplate().find(query, getEntityClass());
    }

    public T insert(final T entity)
    {
        this.publisher.publishEvent(new BeforeEntityCreatedEvent<>(entity));
        getMongoTemplate().insert(entity);
        this.publisher.publishEvent(new AfterEntityCreatedEvent<>(entity));
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
}
