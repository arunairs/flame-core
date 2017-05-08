package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.exception.BranchNotFoundException;
import io.bayberry.repository.exception.ModuleNotFoundException;
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

    public Module insert(Module module) throws BranchNotFoundException, ModuleNotFoundException {
        module.setId(this.idGenerator.nextId());
        module.setCreatedDateTime(LocalDateTime.now());

        this.pushToArchiveModules(module);
        if (module.hasParent()) {
            this.addToParentModuleOrders(module);
        } else {
            this.addToArchiveModuleOrders(module);
        }
        return module;
    }

    private void pushToArchiveModules(Module module) throws BranchNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                new Update().push("archive.modules", module));
        if (result.getN() == 0)
            throw new BranchNotFoundException();
    }

    private void addToParentModuleOrders(Module module) throws ModuleNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getParentId())),
                new Update().push("archive.modules.$.moduleOrders", module.getId()));
        if (result.getN() == 0)
            throw new ModuleNotFoundException();
    }

    private void addToArchiveModuleOrders(Module module) throws ModuleNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())),
                new Update().push("archive.moduleOrders", module.getId()));
        if (result.getN() == 0)
            throw new ModuleNotFoundException();
    }

    public Module get(Long branchId, Long id) {
        Query query = Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(id));
        query.fields().include("archive.modules.$");
        return this.extractFrom(super.get(query));
    }

    private Module extractFrom(Branch branch) {
        if (branch == null || CollectionUtils.isEmpty(branch.getArchive().getModules())) return null;
        return branch.getArchive().getModules().get(0);
    }

    public boolean exists(Long branchId, Long id) {
        return super.exists(Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(id)));
    }

    public Module update(Module module) throws ModuleNotFoundException {
        module.setModifiedDateTime(LocalDateTime.now());

        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getId())),
                new Update().set("archive.modules.$.name", module.getName())
                        .set("archive.modules.$.description", module.getDescription())
                        .set("archive.modules.$.modifiedDateTime", module.getModifiedDateTime()));
        if (result.getN() == 0)
            throw new ModuleNotFoundException();

        Module updatedModule = this.get(module.getBranchId(), module.getId());
        if (updatedModule == null)
            throw new ModuleNotFoundException();

        return updatedModule;
    }

    public void delete(Long branchId, Long id) {
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId).and("archive.modules.moduleOrders").is(id)),
                new Update().pull("archive.modules.$.moduleOrders", id));
        super.updateFirst(Query.query(Criteria.where(ID).is(branchId)),
                new Update().pull("archive.moduleOrders", id)
                        .pull("archive.modules", Query.query(Criteria.where(ID).is(id))));
    }
}
