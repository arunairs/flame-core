package io.bayberry.flame.core.repository;

import io.bayberry.flame.core.repository.entity.Branch;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository extends AbstractMongoRepository<Branch, Long> {

}
