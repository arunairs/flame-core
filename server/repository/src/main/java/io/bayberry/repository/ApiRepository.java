package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.entity.Api;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.exception.ApiNotFoundException;
import io.bayberry.repository.exception.BranchNotFoundException;
import io.bayberry.repository.exception.ModuleNotFoundException;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.bayberry.repository.query.Keys.ID;

@Repository
public class ApiRepository extends AbstractMongoRepository<Branch, Long> {

    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ApiRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Api insert(Api api) throws BranchNotFoundException, ModuleNotFoundException {
        api.setId(this.idGenerator.nextId());
        api.setCreatedDateTime(LocalDateTime.now());
        this.addToArchiveApis(api);
        this.addToModuleApiOrders(api);
        return api;
    }

    private void addToArchiveApis(Api api) throws BranchNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(api.getBranchId())),
                new Update().push("archive.apis", api));
        if (result.getN() == 0)
            throw new BranchNotFoundException();
    }

    private void addToModuleApiOrders(Api api) throws ModuleNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(api.getBranchId())
                        .and("archive.modules._id").is(api.getModuleId())),
                new Update().push("archive.modules.$.apiOrders", api.getId()));
        if (result.getN() == 0)
            throw new ModuleNotFoundException();
    }

    public Api update(Api api) throws ApiNotFoundException {
        api.setModifiedDateTime(LocalDateTime.now());

        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(api.getBranchId())
                .and("archive.apis._id").is(api.getId())), api);
        if (result.getN() == 0)
            throw new ApiNotFoundException();

        return api;
    }

    public void delete(Long branchId, Long id) {
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId).and("archive.modules.apiOrders").is(id)),
                new Update().pull("archive.modules.$.apiOrders", id));
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId)),
                new Update().pull("archive.apis", Query.query(Criteria.where(ID).is(id))));
    }
}
