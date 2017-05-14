package io.bayberry.repository;

import io.bayberry.repository.entity.Document;
import io.bayberry.repository.exception.DocumentNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

    @Override
    public Document insert(Document document) {
        return super.insert(document);
    }

    @Override
    public Document get(Long id) {
        return super.get(id);
    }

    @Override
    public boolean exists(Long id) {
        return super.exists(id);
    }

    public void update(Document entity) throws DocumentNotFoundException {
        if (super.findAndModify(entity) == null) {
            throw new DocumentNotFoundException();
        }
    }
}
