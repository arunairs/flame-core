package io.bayberry.core.repository;

import io.bayberry.core.repository.entity.Branch;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository extends AbstractMongoRepository<Branch, Long> {

}
