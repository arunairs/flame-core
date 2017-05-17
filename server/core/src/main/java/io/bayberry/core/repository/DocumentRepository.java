package io.bayberry.core.repository;

import io.bayberry.core.repository.entity.Document;
import io.bayberry.core.repository.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

    @Override
    public Document insert(Document document) {
        return super.insert(document);
    }

    @Override
    public Optional<Document> get(Long id) {
        return super.get(id);
    }

    @Override
    public boolean exists(Long id) {
        return super.exists(id);
    }

    public void update(Document entity) throws EntityNotFoundException {
        if (super.findAndModify(entity) == null) {
            throw new EntityNotFoundException();
        }
    }
}
