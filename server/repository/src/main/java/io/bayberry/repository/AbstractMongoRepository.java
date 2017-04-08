package io.bayberry.repository;

import com.mongodb.DBObject;
import io.bayberry.repository.entity.Persistable;
import io.bayberry.repository.query.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

public abstract class AbstractMongoRepository<T extends Persistable<ID>, ID extends Serializable> {

    @Autowired
    private MongoTemplate mongoTemplate;
    private Class<T> entityClass;

    public T update(final T entity) {
        return update(new Query().addCriteria(Criteria.where(Keys.ID).is(entity.getId())), entity);
    }

    public T update(final Query query, final T entity) {
        Update update = Update.fromDBObject((DBObject) getMongoTemplate().getConverter().convertToMongoType(entity));
        T result = update(query, update);
        return result;
    }

    public T update(final Query query, final Update update) {
        T result = getMongoTemplate().findAndModify(query, update, options().upsert(false).returnNew(true), this.entityClass);
        return result;
    }

    public T get(final ID id) {
        return id == null ? null : getMongoTemplate().findById(id, this.entityClass);
    }

    public T findOne(final Query query) {
        return getMongoTemplate().findOne(query, this.entityClass);
    }

    public boolean exists(final ID id) {
        return getMongoTemplate().exists(new Query().addCriteria(Criteria.where(Keys.ID).is(id)), this.entityClass);
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
        return getMongoTemplate().find(new Query().addCriteria(Criteria.where(Keys.ID).in(ids)), this.entityClass);
    }

    public T insert(final T entity) {
        getMongoTemplate().insert(entity);
        return entity;
    }

    public Iterable<T> insert(final Iterable<T> entities) {
        for (T entity : entities) {
            insert(entity);
        }
        return entities;
    }

    public long count(final Query query) {
        return getMongoTemplate().count(query, this.entityClass);
    }

    public void delete(final ID id) {
        getMongoTemplate().remove(new Query().addCriteria(Criteria.where("_id").is(id)), this.entityClass);
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

    @PostConstruct
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
