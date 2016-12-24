package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long>
{
    @Override
    protected Class<Branch> getEntityClass()
    {
        return Branch.class;
    }

    @Override
    public Branch get(Long id)
    {
        if (id == null) return null;
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        query.fields().exclude("archive");
        return this.findOne(query);
    }

    public Archive getArchive(Long branchId)
    {
        if (branchId == null) return null;
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(branchId));
        query.fields().include("archive");
        return this.findOne(query).getArchive();
    }
}
