package io.bayberry.core.repository;

import io.bayberry.core.repository.entity.Branch;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

import static io.bayberry.core.constant.Fields.ID;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    @Override
    public Branch insert(Branch branch) {
        return super.insert(branch);
    }

    @Override
    public Optional<Branch> get(Long id) {
        Query query = Query.query(Criteria.where(ID).is(id));
        query.fields().exclude("archive");
        return super.get(query);
    }

    @Override
    public boolean exists(Long id) {
        return super.exists(id);
    }

    public boolean exists(Long documentId, String branchName) {
        return this.exists(Query.query(Criteria.where("documentId").is(documentId).and("name").is(branchName)));
    }

    public int update(Branch branch) {
        branch.setLastModifiedTime(Instant.now());
        Update update = new Update();
        update.set("name", branch.getName());
        update.set("modifiedDateTime", branch.getLastModifiedTime());
        return super.updateFirst(branch.getId(), update).getN();
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
