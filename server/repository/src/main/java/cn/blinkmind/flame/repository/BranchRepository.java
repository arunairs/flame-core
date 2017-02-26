package cn.blinkmind.flame.repository;

import cn.blinkmind.flame.repository.model.Archive;
import cn.blinkmind.flame.repository.model.Branch;
import cn.blinkmind.flame.repository.model.Commit;
import cn.blinkmind.flame.repository.query.Keys;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    public BranchRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        super(applicationEventPublisher, mongoTemplate);
    }

    @Override
    public Branch get(final Long id) {
        if (id == null) return null;
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(id));
        query.fields().exclude(Keys.ARCHIVE);
        return this.findOne(query);
    }

    public boolean exists(final String name, final Long documentId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("documentRef._id").is(documentId).and("name").is(name);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public boolean exists(final Long id, final Long documentId) {
        Query query = new Query();
        Criteria criteria = Criteria.where(Keys.ID).is(id).and("documentRef._id").is(documentId);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public Archive getArchive(final Long id) {
        if (id == null) return null;
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(id));
        query.fields().include(Keys.ARCHIVE);
        return this.findOne(query).getArchive();
    }

    public Branch updateArchive(final Long branchId, final Commit<Archive> commit, final User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(branchId).and(Keys.HEADERS + "." + Commit.VERSION).lte(commit.getHeaders().getLong(Commit.VERSION)));
        Update update = new Update();
        update.set(Keys.ARCHIVE, commit.getPayload());
        update.set(Keys.HEADERS + "." + Commit.SN, commit.getHeaders().getString(Commit.SN));
        update.inc(Keys.HEADERS + "." + Commit.VERSION, 1);
        return this.update(query, update);
    }
}
