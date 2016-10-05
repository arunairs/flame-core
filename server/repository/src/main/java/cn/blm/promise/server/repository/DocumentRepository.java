package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:43 PM
 */
@Repository
public interface DocumentRepository extends MongoRepository<Document, Long>
{
}
