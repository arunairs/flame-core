package io.bayberry.repository;

import io.bayberry.repository.model.Document;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

    public DocumentRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        super(applicationEventPublisher, mongoTemplate);
    }
}
