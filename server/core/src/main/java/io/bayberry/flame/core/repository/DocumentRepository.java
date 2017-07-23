package io.bayberry.flame.core.repository;

import io.bayberry.flame.core.repository.entity.Document;
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

    public int update(Document entity) {
        return super.updateFirst(entity).getN();
    }
}
