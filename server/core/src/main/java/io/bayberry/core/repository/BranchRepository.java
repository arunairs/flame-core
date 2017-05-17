package io.bayberry.core.repository;

import com.mongodb.WriteResult;
import io.bayberry.core.repository.entity.Branch;
import io.bayberry.core.repository.exception.EntityNotFoundException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public void update(Branch branch) throws EntityNotFoundException {
        branch.setLastModifiedTime(LocalDateTime.now());
        Update update = new Update();
        update.set("name", branch.getName());
        update.set("modifiedDateTime", branch.getLastModifiedTime());
        WriteResult result = super.updateFirst(branch.getId(), update);
        if (result.getN() == 0)
            throw new EntityNotFoundException();
    }

    @Override
    public void remove(Long id) {
        super.remove(id);
    }
}
