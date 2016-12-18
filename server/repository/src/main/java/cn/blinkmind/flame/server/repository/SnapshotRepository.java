package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@org.springframework.stereotype.Repository
public class SnapshotRepository extends AbstractMongoRepository<Snapshot, Long> {

    @Override
    protected Class<Snapshot> getEntityClass() {
        return Snapshot.class;
    }

    @Override
    public Snapshot get(Long id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(ID).is(id)),
                Aggregation.lookup("branches", "branchRef._id", ID, "joinBranches")
        );
        DBObject result = getMongoTemplate().aggregate(aggregation, Snapshot.class, DBObject.class).getUniqueMappedResult();
        Snapshot snapshot = getMongoTemplate().getConverter().read(Snapshot.class, result);
        BasicDBList joinBranches = (BasicDBList) result.get("joinBranches");
        Branch branch = getMongoTemplate().getConverter().read(Branch.class, (DBObject) joinBranches.get(0));
        snapshot.setBranch(branch);
        return snapshot;
    }

    public Snapshot get(Branch branch, User user) {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
                .and("creatorRef._id").is(user.getId());
        query.addCriteria(criteria);
        return this.findOne(query);
    }

    public Snapshot require(Branch branch, User user) {
        Snapshot snapshot = get(branch, user);
        if (snapshot == null) throw new ResourceNotFoundException();
        return snapshot;
    }

    public boolean exists(Branch branch, User user) {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
                .and("creatorRef._id").is(user.getId());
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public Snapshot updateArchive(long snapshotId, Archive archive, User user) {
        archive.refresh();
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(snapshotId));
        Update update = new Update();
        update.set("archive", archive);
        return this.update(query, update);
    }
}
