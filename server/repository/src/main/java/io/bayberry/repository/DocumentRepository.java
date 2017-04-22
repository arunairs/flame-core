package io.bayberry.repository;

import io.bayberry.repository.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

}
