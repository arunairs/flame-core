package io.bayberry.repository;

import io.bayberry.repository.model.Branch;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    public boolean exists(String branchName, Long documentId) {
        return this.exists(Query.query(Criteria.where("documentId").is(documentId).and("name").is(branchName)));
    }

    @Override
    public Branch get(Long id) {
        return super.get(id);
    }

    @Override
    public boolean exists(Long id) {
        return super.exists(id);
    }

    @Override
    public Branch insert(Branch branch) {
        return super.insert(branch);
    }

    @Override
    public Branch updateAndReturn(Branch branch) {
        return super.updateAndReturn(branch);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
