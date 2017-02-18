package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.Document;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.query.Keys;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long> {

    public DocumentRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        super(applicationEventPublisher, mongoTemplate);
    }

    @Override
    public Document get(final Long id) {
        Document document = null;
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(Keys.ID).is(id)),
                Aggregation.lookup("users", "creatorRef._id", Keys.ID, "creators")
        );
        DBObject result = getMongoTemplate().aggregate(aggregation, Document.class, DBObject.class).getUniqueMappedResult();
        if (result != null) {
            document = getMongoTemplate().getConverter().read(Document.class, result);
            if (document != null) {
                BasicDBList creators = (BasicDBList) result.get("creators");
                if (CollectionUtils.isNotEmpty(creators)) {
                    User creator = getMongoTemplate().getConverter().read(User.class, (DBObject) creators.get(0));
                    document.setCreator(creator);
                }
            }
        }
        return document;
    }
}
