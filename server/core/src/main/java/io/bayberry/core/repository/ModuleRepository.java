package io.bayberry.core.repository;

import com.mongodb.WriteResult;
import io.bayberry.core.repository.entity.Branch;
import io.bayberry.core.repository.entity.Module;
import io.bayberry.core.repository.id.IdGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

import static io.bayberry.core.constant.Fields.ID;

@Repository
public class ModuleRepository extends AbstractMongoRepository<Branch, Long> {

    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Optional<Module> insert(Module module) {
        module.setId(this.idGenerator.nextId());
        module.setCreatedTime(Instant.now());

        if (this.pushToArchiveModules(module).getN() == 0)
            return Optional.empty();

        if (module.hasParent()) {
            if (this.addToParentModuleOrders(module).getN() == 0) {
                this.delete(module.getBranchId(), module.getId());
                return Optional.empty();
            }
        } else {
            if (this.addToArchiveModuleOrders(module).getN() == 0) {
                this.delete(module.getBranchId(), module.getId());
                return Optional.empty();
            }
        }
        return Optional.of(module);
    }

    private WriteResult pushToArchiveModules(Module module) {
        return super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                new Update().push("archive.modules", module));
    }

    private WriteResult addToParentModuleOrders(Module module) {
        return super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getParentId())),
                new Update().push("archive.modules.$.moduleOrders", module.getId()));
    }

    private WriteResult addToArchiveModuleOrders(Module module) {
        return super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                new Update().push("archive.moduleOrders", module.getId()));
    }

    public Optional<Module> get(Long branchId, Long id) {
        Query query = Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(id));
        query.fields().include("archive.modules.$");
        return super.get(query).map(this::extract);
    }

    private Module extract(Branch branch) {
        if (CollectionUtils.isEmpty(branch.getArchive().getModules())) return null;
        return branch.getArchive().getModules().get(0);
    }

    public boolean exists(Long branchId, Long id) {
        return super.exists(Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(id)));
    }

    public int update(Module module) {
        module.setLastModifiedTime(Instant.now());

        return super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getId())),
                new Update().set("archive.modules.$.name", module.getName())
                        .set("archive.modules.$.description", module.getDescription())
                        .set("archive.modules.$.modifiedDateTime", module.getLastModifiedTime())).getN();
    }

    public void delete(Long branchId, Long id) {
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId).and("archive.modules.moduleOrders").is(id)),
                new Update().pull("archive.modules.$.moduleOrders", id));
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId)),
                new Update().pull("archive.moduleOrders", id)
                        .pull("archive.modules", Query.query(Criteria.where(ID).is(id)))
                        .pull("archive.apis", Query.query(Criteria.where("moduleId").is(id))));
    }
}
