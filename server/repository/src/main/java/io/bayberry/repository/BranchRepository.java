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
}
