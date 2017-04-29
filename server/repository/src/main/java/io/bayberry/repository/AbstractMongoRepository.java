package io.bayberry.repository;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import io.bayberry.repository.model.Persistable;
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

    protected T insert(final T entity) {
        getMongoTemplate().insert(entity);
        return entity;
    }

    protected Iterable<T> insert(final Iterable<T> entities) {
        for (T entity : entities) {
            insert(entity);
        }
        return entities;
    }

    protected T get(final ID id) {
        return id == null ? null : getMongoTemplate().findById(id, this.entityClass);
    }

    protected T get(final Query query) {
        return getMongoTemplate().findOne(query, this.entityClass);
    }

    protected boolean exists(final ID id) {
        return getMongoTemplate().exists(Query.query(Criteria.where(Keys.ID).is(id)), this.entityClass);
    }

    protected boolean exists(final Query query) {
        return getMongoTemplate().exists(query, this.entityClass);
    }

    protected List<T> findAll(final Query query) {
        return getMongoTemplate().find(query, this.entityClass);
    }

    protected List<T> findAll() {
        return getMongoTemplate().findAll(this.entityClass);
    }

    protected List<T> findAll(final Iterable<ID> ids) {
        return getMongoTemplate().find(Query.query(Criteria.where(Keys.ID).in(ids)), this.entityClass);
    }

    protected T findAndModify(final T entity) {
        return findAndModify(Query.query(Criteria.where(Keys.ID).is(entity.getId())), entity);
    }

    protected T findAndModify(final Query query, final T entity) {
        Update update = Update.fromDBObject((DBObject) getMongoTemplate().getConverter().convertToMongoType(entity));
        return findAndModify(query, update);
    }

    protected T findAndModify(final ID id, final Update update) {
        return findAndModify(Query.query(Criteria.where(Keys.ID).is(id)), update);
    }

    protected T findAndModify(final Query query, final Update update) {
        return getMongoTemplate().findAndModify(query, update, options().upsert(false).returnNew(true), this.entityClass);
    }

    protected WriteResult updateFirst(final ID id, final Update update) {
        return updateFirst(Query.query(Criteria.where(Keys.ID).is(id)), update);
    }

    protected WriteResult updateFirst(final Query query, final Update update) {
        return getMongoTemplate().updateFirst(query, update, this.entityClass);
    }

    protected WriteResult updateMulti(final Query query, final Update update) {
        return getMongoTemplate().updateMulti(query, update, this.entityClass);
    }

    protected long count(final Query query) {
        return getMongoTemplate().count(query, this.entityClass);
    }

    protected void remove(final ID id) {
        getMongoTemplate().remove(Query.query(Criteria.where("_id").is(id)), this.entityClass);
    }

    protected void remove(final T entity) {
        getMongoTemplate().remove(entity);
    }

    protected void remove(final Query query) {
        getMongoTemplate().remove(query, this.entityClass);
    }

    protected void remove(final Iterable<T> entities) {
        for (T entity : entities) {
            remove(entity);
        }
    }

    protected BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode) {
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
