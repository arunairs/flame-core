package io.bayberry.repository;

import io.bayberry.repository.model.Archive;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Commit;
import io.bayberry.repository.model.User;
import io.bayberry.repository.query.Keys;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long> {

    public boolean exists(final String name, final Long documentId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("documentRef._id").is(documentId).and("name").is(name);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public boolean exists(final Long id, final Long documentId) {
        Query query = new Query();
        Criteria criteria = Criteria.where(Keys.ID).is(id).and("documentRef._id").is(documentId);
        query.addCriteria(criteria);
        return this.exists(query);
    }

    public Branch updateArchive(final Long id, final Commit<Archive> commit, final User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Keys.ID).is(id).and(Keys.HEADERS + "." + Commit.VERSION).lte(commit.getHeaders().getLong(Commit.VERSION)));
        Update update = new Update();
        update.set(Keys.ARCHIVE, commit.getPayload());
        update.set(Keys.HEADERS + "." + Commit.SN, commit.getHeaders().getString(Commit.SN));
        update.inc(Keys.HEADERS + "." + Commit.VERSION, 1);
        return this.update(query, update);
    }
}
