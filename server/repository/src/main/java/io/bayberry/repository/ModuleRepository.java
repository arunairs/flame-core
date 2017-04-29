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
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

import static io.bayberry.repository.query.Keys.ID;

@Repository
public class ModuleRepository extends AbstractMongoRepository<Branch, Long> {

    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Module create(Module module) {
        module.setId(idGenerator.nextId());
        module.setCreatedDateTime(LocalDateTime.now());

        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                new Update().push("archive.modules", module));
        if (result.getN() == 0) return null;

        if (module.getParentId() == null) {
            result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                    new Update().push("archive.moduleOrder", module.getId()));
        } else {
            result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                            .and("archive.modules._id").is(module.getParentId())),
                    new Update().push("archive.modules.$.moduleOrder", module.getId()));
        }
        return result.getN() == 0 ? null : module;
    }

    public Module get(Long moduleId, Long branchId) {
        Query query = Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(moduleId));
        query.fields().include("archive.modules.$");
        return this.extractFrom(super.get(query));
    }

    public boolean exists(Long moduleId, Long branchId) {
        return super.exists(Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(moduleId)));
    }

    public Module update(Module module) {
        module.setModifiedDateTime(LocalDateTime.now());
        super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getId())),
                new Update().set("archive.modules.$.name", module.getName())
                        .set("archive.modules.$.description", module.getDescription())
                        .set("archive.modules.$.modifiedDateTime", module.getModifiedDateTime()));
        return this.get(module.getId(), module.getBranchId());
    }

    public void delete(Long moduleId, Long branchId) {
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId).and("archive.modules.moduleOrder").is(moduleId)),
                new Update().pull("archive.modules.$.moduleOrder", moduleId));
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId)),
                new Update().pull("archive.moduleOrder", moduleId)
                        .pull("archive.modules", Query.query(Criteria.where(ID).is(moduleId))));
    }

    private Module extractFrom(Branch branch) {
        if (branch == null || CollectionUtils.isEmpty(branch.getArchive().getModules())) return null;
        return branch.getArchive().getModules().get(0);
    }
}
