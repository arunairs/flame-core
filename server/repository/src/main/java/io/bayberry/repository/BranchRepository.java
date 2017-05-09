package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.exception.BranchNotFoundException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.bayberry.repository.query.Keys.ID;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    @Override
    public Branch insert(Branch branch) {
        return super.insert(branch);
    }

    @Override
    public Branch get(Long id) {
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

    public Branch update(Branch branch) throws BranchNotFoundException {
        branch.setModifiedDateTime(LocalDateTime.now());
        Update update = new Update();
        update.set("name", branch.getName());
        update.set("modifiedDateTime", branch.getModifiedDateTime());
        WriteResult result = super.updateFirst(branch.getId(), update);
        if (result.getN() == 0)
            throw new BranchNotFoundException();
        return branch;
    }

    @Override
    public void remove(Long id) {
        super.remove(id);
    }
}
