package io.bayberry.repository;

import io.bayberry.repository.model.Document;
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

    public Document update(Document entity) {
        return super.findAndModify(entity);
    }
}
