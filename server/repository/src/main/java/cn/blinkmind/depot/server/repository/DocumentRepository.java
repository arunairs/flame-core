package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Document;
import cn.blinkmind.depot.server.repository.entity.User;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:43 PM
 */
@Repository
public class DocumentRepository extends AbstractMongoRepository<Document, Long>
{
	@Override
	protected Class<Document> getEntityClass()
	{
		return Document.class;
	}

	@Override
	public Document get(Long id)
	{
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where(ID).is(id)),
				Aggregation.lookup("users", "creatorRef._id", ID, "joinCreators")
		);
		DBObject result = getMongoTemplate().aggregate(aggregation, Document.class, DBObject.class).getUniqueMappedResult();
		Document document = getMongoTemplate().getConverter().read(Document.class, result);
		BasicDBList joinCreators = (BasicDBList) result.get("joinCreators");
		User creator = getMongoTemplate().getConverter().read(User.class, (DBObject) joinCreators.get(0));
		document.setCreator(creator);
		return document;
	}
}
