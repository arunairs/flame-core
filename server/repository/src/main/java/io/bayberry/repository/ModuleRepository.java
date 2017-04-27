package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Module;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.bayberry.repository.query.Keys.ID;

@Repository
public class ModuleRepository extends AbstractMongoRepository<Branch, Long> {

    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Module create(Module module, Long branchId) {
        module.setId(idGenerator.nextId());
        module.setCreatedDateTime(LocalDateTime.now());

        WriteResult result = super.update(Query.query(Criteria.where(ID).is(branchId)),
                new Update().push("archive.modules", module));
        if (result.getN() == 0) return null;

        if (module.getParentId() == null) {
            result = super.update(Query.query(Criteria.where(ID).is(branchId)),
                    new Update().push("archive.moduleOrder", module.getId()));
        } else {
            result = super.update(Query.query(Criteria.where(ID).is(branchId)
                            .and("archive.modules._id").is(module.getParentId())),
                    new Update().push("archive.modules.$.moduleOrder", module.getId()));
        }
        return result.getN() == 0 ? null : module;
    }

    public Module update(Module module, Long branchId) {
        module.setModifiedDateTime(LocalDateTime.now());
        WriteResult result = super.update(Query.query(Criteria.where(ID).is(branchId)
                        .and("archive.modules._id").is(module.getId())),
                new Update().set("archive.modules.$.name", module.getName())
                        .set("archive.modules.$.description", module.getDescription())
                        .set("archive.modules.$.modifiedDateTime", module.getModifiedDateTime()));
        return result.getN() == 0 ? null : module;
    }

    public void delete(Long moduleId, Long branchId) {
        super.update(Query.query(Criteria.where(ID).is(branchId).and("archive.modules.moduleOrder").is(moduleId)),
                new Update().pull("archive.modules.$.moduleOrder", moduleId));
        super.update(Query.query(Criteria.where(ID).is(branchId)),
                new Update().pull("archive.moduleOrder", moduleId)
                        .pull("archive.modules", Query.query(Criteria.where(ID).is(moduleId))));
    }

    public boolean exists(Long moduleId, Long branchId) {
        return super.exists(Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(moduleId)));
    }
}
