package cn.blinkmind.flame.repository;

import cn.blinkmind.flame.repository.entity.Persistable;
import cn.blinkmind.flame.repository.query.Keys;
import cn.blinkmind.flame.repository.event.*;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

public abstract class AbstractMongoRepository<T extends Persistable<ID>, ID extends Serializable> {
    private ApplicationEventPublisher applicationEventPublisher;
    private MongoTemplate mongoTemplate;
    private Class<T> entityClass;

    @Autowired
    public AbstractMongoRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.mongoTemplate = mongoTemplate;
        this.init();
    }

    public T update(final T entity) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(entity.getId()));
        return update(query, entity);
    }

    public T update(final Query query, final T entity) {
        this.applicationEventPublisher.publishEvent(new BeforeEntityUpdatedEvent<>(entity));
        DBObject object = (DBObject) getMongoTemplate().getConverter().convertToMongoType(entity);
        Update update = Update.fromDBObject(object);
        T result = update(query, update);
        this.applicationEventPublisher.publishEvent(new AfterEntityUpdatedEvent<>(entity));
        return result;
    }

    public T update(final Query query, final Update update) {
        this.applicationEventPublisher.publishEvent(new BeforeUpdateAppliedEvent(update));
        T result = getMongoTemplate().findAndModify(query, update, options().upsert(false).returnNew(true), this.entityClass);
        this.applicationEventPublisher.publishEvent(new AfterUpdateAppliedEvent(update));
        return result;
    }

    public T get(final ID id) {
        return id == null ? null : getMongoTemplate().findById(id, this.entityClass);
    }

    public T get(final T entity) {
        return entity == null ? null : get(entity.getId());
    }


    public T findOne(final Query query) {
        return getMongoTemplate().findOne(query, this.entityClass);
    }

    public boolean exists(final ID id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(id));
        return getMongoTemplate().exists(query, this.entityClass);
    }

    public boolean exists(final Query query) {
        return getMongoTemplate().exists(query, this.entityClass);
    }

    public List<T> findAll(final Query query) {
        return getMongoTemplate().find(query, this.entityClass);
    }

    public List<T> findAll() {
        return getMongoTemplate().findAll(this.entityClass);
    }

    public List<T> findAll(final Iterable<ID> ids) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).in(ids));
        return getMongoTemplate().find(query, this.entityClass);
    }

    public T insert(final T entity) {
        this.applicationEventPublisher.publishEvent(new BeforeEntityCreatedEvent<>(entity));
        getMongoTemplate().insert(entity);
        this.applicationEventPublisher.publishEvent(new AfterEntityCreatedEvent<>(entity));
        return entity;
    }

    public List<T> insert(final Iterable<T> entities) {
        for (T entity : entities) {
            insert(entity);
        }
        return (List<T>) entities;
    }

    public long count(final Query query) {
        return getMongoTemplate().count(query, this.entityClass);
    }

    public void delete(final ID id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        getMongoTemplate().remove(query, this.entityClass);
    }

    public void delete(final T entity) {
        getMongoTemplate().remove(entity);
    }

    public void delete(final Query query) {
        getMongoTemplate().remove(query, this.entityClass);
    }

    public void delete(final Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    public BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode) {
        return getMongoTemplate().bulkOps(bulkMode, this.entityClass);
    }

    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    private void init() {
        this.entityClass = getEntityClass(this.getClass());
    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass(Class<?> repositoryClass) {
        Class<?> superClass = repositoryClass.getSuperclass();
        if (superClass == AbstractMongoRepository.class) {
            ParameterizedType parameterizedType = (ParameterizedType) repositoryClass.getGenericSuperclass();
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        return getEntityClass(superClass);
    }
}
