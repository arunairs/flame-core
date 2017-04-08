package io.bayberry.repository;

import io.bayberry.repository.entity.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

}
