package io.bayberry.repository;

import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.Module;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static io.bayberry.repository.query.Keys.ID;
import static io.bayberry.repository.query.Keys.MODULES;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    public boolean exists(String branchName, Long documentId) {
        return this.exists(Query.query(Criteria.where("documentRef._id").is(documentId).and("name").is(branchName)));
    }

    public void addModule(Module module, Long branchId) {
        super.update(Query.query(Criteria.where(ID).is(branchId)), new Update().pull(MODULES, module));
    }
}
