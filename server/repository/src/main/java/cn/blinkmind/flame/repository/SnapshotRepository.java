package cn.blinkmind.flame.repository;

import cn.blinkmind.flame.repository.model.*;
import cn.blinkmind.flame.repository.query.Keys;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@org.springframework.stereotype.Repository
public class SnapshotRepository extends AbstractMongoRepository<Snapshot, Long> {

    public SnapshotRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        super(applicationEventPublisher, mongoTemplate);
    }

    @Override
    public Snapshot get(final Long id) {
        Snapshot snapshot = null;
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(Keys.ID).is(id)),
                Aggregation.lookup("branches", "branchRef._id", Keys.ID, "branches")
        );
        DBObject result = getMongoTemplate().aggregate(aggregation, Snapshot.class, DBObject.class).getUniqueMappedResult();
        if (result != null) {
            snapshot = getMongoTemplate().getConverter().read(Snapshot.class, result);
            if (snapshot != null) {
                BasicDBList branches = (BasicDBList) result.get("branches");
                Branch branch = getMongoTemplate().getConverter().read(Branch.class, (DBObject) branches.get(0));
//TODO:??
                //                snapshot.setBranch(branch);
            }
        }
        return snapshot;
    }

    public Snapshot get(final Branch branch, final User user) {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
                .and("creatorRef._id").is(user.getId());
        query.addCriteria(criteria);
        return this.findOne(query);
    }

    public boolean exists(final String name, final Long branchId, final User user) {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branchId)
                .and("creatorRef._id").is(user.getId())
                .and("name").is(name);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public boolean exists(final Long id, final Long branchId) {
        Query query = new Query();
        Criteria criteria = Criteria.where(Keys.ID).is(id).and("branchRef._id").is(branchId);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public Snapshot updateArchive(final Long snapshotId, final Headers headers, final Archive archive) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(snapshotId));
        Update update = new Update();
        update.set(Keys.ARCHIVE, archive);
        update.set(Keys.HEADERS, headers);
        return this.update(query, update);
    }

    public Snapshot updateHeaders(final Long snapshotId, final Headers headers) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(snapshotId));
        Update update = new Update();
        update.set(Keys.HEADERS, headers);
        return this.update(query, update);
    }
}
