package io.bayberry.flame.core.repository;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import io.bayberry.flame.common.util.ReflectionUtils;
import io.bayberry.flame.core.constant.Fields;
import io.bayberry.flame.core.repository.entity.Persistable;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

public abstract class AbstractMongoRepository<T extends Persistable<ID>, ID extends Serializable> {

    @Autowired
    private MongoTemplate mongoTemplate;
    private Class<T> entityClass;

    protected T insert(final T entity) {
        getMongoTemplate().insert(entity);
        return entity;
    }

    protected Iterable<T> insert(final Iterable<T> entities) throws Exception {
        for (T entity : entities) {
            insert(entity);
        }
        return entities;
    }

    protected Optional<T> get(final ID id) {
        return Optional.ofNullable(getMongoTemplate().findById(id, this.entityClass));
    }

    protected Optional<T> get(final Query query) {
        return Optional.ofNullable(getMongoTemplate().findOne(query, this.entityClass));
    }

    protected boolean exists(final ID id) {
        return getMongoTemplate().exists(Query.query(Criteria.where(Fields.ID).is(id)), this.entityClass);
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
        return getMongoTemplate().find(Query.query(Criteria.where(Fields.ID).in(ids)), this.entityClass);
    }

    protected T findAndModify(final T entity) {
        return findAndModify(Query.query(Criteria.where(Fields.ID).is(entity.getId())), entity);
    }

    protected T findAndModify(final Query query, final T entity) {
        Update update = getUpdateFromObject(entity);
        return findAndModify(query, update);
    }

    protected T findAndModify(final ID id, final Update update) {
        return findAndModify(Query.query(Criteria.where(Fields.ID).is(id)), update);
    }

    protected T findAndModify(final Query query, final Update update) {
        return getMongoTemplate().findAndModify(query, update, options().upsert(false).returnNew(true), this.entityClass);
    }

    protected <E extends Persistable<ID>> WriteResult updateFirst(final E entity) {
        return updateFirst(Query.query(Criteria.where(Fields.ID).is(entity.getId())), entity);
    }

    protected <E extends Persistable<ID>> WriteResult updateFirst(final Query query, final E entity) {
        return getMongoTemplate().updateFirst(query, getUpdateFromObject(entity), this.entityClass);
    }

    protected WriteResult updateFirst(final ID id, final Update update) {
        return updateFirst(Query.query(Criteria.where(Fields.ID).is(id)), update);
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

    protected void delete(final ID id) {
        getMongoTemplate().remove(Query.query(Criteria.where("_id").is(id)), this.entityClass);
    }

    protected void delete(final T entity) {
        getMongoTemplate().remove(entity);
    }

    protected void delete(final Query query) {
        getMongoTemplate().remove(query, this.entityClass);
    }

    protected void delete(final Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    protected BulkOperations bulkOps(final BulkOperations.BulkMode bulkMode) {
        return getMongoTemplate().bulkOps(bulkMode, this.entityClass);
    }

    private MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @PostConstruct
    private void init() {
        this.entityClass = getEntityClass();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        return (Class<T>) ReflectionUtils.getParameterizedTypesOf(this.getClass(), AbstractMongoRepository.class)[0];
    }

    private <E> Update getUpdateFromObject(E entity) {
        return Update.fromDBObject((DBObject) getMongoTemplate().getConverter().convertToMongoType(entity));
    }

    protected <E> DBObject convertToDBObject(E object) {
        return convertToDBObject(object, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    protected <E> DBObject convertToDBObject(E object, String... ignoreProperties) {
        DBObject dbObject = (DBObject) getMongoTemplate().getConverter().convertToMongoType(object);
        for (String property : ignoreProperties) {
            dbObject.removeField(property);
        }
        return dbObject;
    }
}
