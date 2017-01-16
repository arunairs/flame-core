package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import cn.blinkmind.flame.server.repository.query.Keys;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@org.springframework.stereotype.Repository
public class SnapshotRepository extends AbstractMongoRepository<Snapshot, Long>
{
    @Override
    protected Class<Snapshot> getEntityClass()
    {
        return Snapshot.class;
    }

    @Override
    public Snapshot get(Long id)
    {
        Snapshot snapshot = null;
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(Keys.ID).is(id)),
                Aggregation.lookup("branches", "branchRef._id", Keys.ID, "branches")
        );
        DBObject result = getMongoTemplate().aggregate(aggregation, Snapshot.class, DBObject.class).getUniqueMappedResult();
        if (result != null)
        {
            snapshot = getMongoTemplate().getConverter().read(Snapshot.class, result);
            if (snapshot != null)
            {
                BasicDBList branches = (BasicDBList) result.get("branches");
                Branch branch = getMongoTemplate().getConverter().read(Branch.class, (DBObject) branches.get(0));
                snapshot.setBranch(branch);
            }
        }
        return snapshot;
    }

    public Snapshot get(Branch branch, User user)
    {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
                .and("creatorRef._id").is(user.getId());
        query.addCriteria(criteria);
        return this.findOne(query);
    }

    public Snapshot require(Branch branch, User user)
    {
        Snapshot snapshot = get(branch, user);
        if (snapshot == null) throw new ResourceNotFoundException();
        return snapshot;
    }

    public boolean exists(String name, long branchId, User user)
    {
        Query query = new Query();
        Criteria criteria = Criteria.where("branchRef._id").is(branchId)
                .and("creatorRef._id").is(user.getId())
                .and("name").is(name);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public Snapshot updateArchive(long snapshotId, Archive archive, User user)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(snapshotId));
        Update update = new Update();
        update.set("archive", archive);
        return this.update(query, update);
    }
}
