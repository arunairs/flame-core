package io.bayberry.flame.core.repository;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import io.bayberry.flame.core.constant.Fields;
import io.bayberry.flame.core.repository.entity.Api;
import io.bayberry.flame.core.repository.entity.Branch;
import io.bayberry.flame.core.repository.id.IdGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public class ApiRepository extends AbstractMongoRepository<Branch, Long> {

    private final String UPDATE_API_KEY_PREFIX = "archive.apis.$.";
    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ApiRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Optional<Api> insert(Api api) {
        api.setId(this.idGenerator.nextId());
        api.setCreatedTime(Instant.now());
        if (this.addToArchiveApis(api).getN() == 0 || this.addToModuleApiOrders(api).getN() == 0) {
            return Optional.empty();
        }
        return Optional.of(api);
    }

    private WriteResult addToArchiveApis(Api api) {
        return super.updateFirst(Query.query(Criteria.where(Fields.ID).is(api.getBranchId())),
                new Update().push("archive.apis", api));
    }

    private WriteResult addToModuleApiOrders(Api api) {
        return super.updateFirst(Query.query(Criteria.where(Fields.ID).is(api.getBranchId())
                        .and("archive.modules._id").is(api.getModuleId())),
                new Update().push("archive.modules.$.apiOrders", api.getId()));
    }

    public Optional<Api> get(Long branchId, Long id) {
        Query query = Query.query(Criteria.where(Fields.ID).is(branchId).and("archive.apis._id").is(id));
        query.fields().include("archive.apis.$");
        return super.get(query).map(this::extract);
    }

    private Api extract(Branch branch) {
        if (branch == null || CollectionUtils.isEmpty(branch.getArchive().getApis())) return null;
        return branch.getArchive().getApis().get(0);
    }

    public int update(Api api) {
        api.setLastModifiedTime(Instant.now());
        return super.updateFirst(Query.query(Criteria.where(Fields.ID).is(api.getBranchId())
                .and("archive.apis._id").is(api.getId())), getApiUpdate(api)).getN();
    }

    private Update getApiUpdate(Api api) {
        DBObject dbObject = super.convertToDBObject(api, Fields.CREATOR_ID, Fields.CREATED_TIME);
        Update update = new Update();
        dbObject.keySet().forEach(key -> update.set(UPDATE_API_KEY_PREFIX + key, dbObject.get(key)));
        return update;
    }

    public void delete(Long branchId, Long id) {
        super.updateFirst(Query.query(Criteria.where(Fields.ID).is(branchId).and("archive.modules.apiOrders").is(id)),
                new Update().pull("archive.modules.$.apiOrders", id));
        super.updateFirst(Query.query(Criteria.where(Fields.ID).is(branchId)),
                new Update().pull("archive.apis", Query.query(Criteria.where(Fields.ID).is(id))));
    }
}
