package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.exception.BranchNotFoundException;
import io.bayberry.repository.exception.ModuleNotFoundException;
import io.bayberry.repository.util.IdGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.bayberry.repository.constant.Fields.ID;

@Repository
public class ModuleRepository extends AbstractMongoRepository<Branch, Long> {

    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Module insert(Module module) throws BranchNotFoundException, ModuleNotFoundException {
        module.setId(this.idGenerator.nextId());
        module.setCreatedTime(LocalDateTime.now());

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
        if (result.getN() == 0) {
            this.delete(module.getBranchId(), module.getId());
            throw new BranchNotFoundException();
        }
    }

    private void addToParentModuleOrders(Module module) throws ModuleNotFoundException {
        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getParentId())),
                new Update().push("archive.modules.$.moduleOrders", module.getId()));
        if (result.getN() == 0) {
            this.delete(module.getBranchId(), module.getId());
            throw new ModuleNotFoundException();
        }
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
        return this.extract(super.get(query));
    }

    private Module extract(Branch branch) {
        if (branch == null || CollectionUtils.isEmpty(branch.getArchive().getModules())) return null;
        return branch.getArchive().getModules().get(0);
    }

    public boolean exists(Long branchId, Long id) {
        return super.exists(Query.query(Criteria.where(ID).is(branchId).and("archive.modules._id").is(id)));
    }

    public void update(Module module) throws ModuleNotFoundException {
        module.setLastModifiedTime(LocalDateTime.now());

        WriteResult result = super.updateFirst(Query.query(Criteria.where(ID).is(module.getBranchId())
                        .and("archive.modules._id").is(module.getId())),
                new Update().set("archive.modules.$.name", module.getName())
                        .set("archive.modules.$.description", module.getDescription())
                        .set("archive.modules.$.modifiedDateTime", module.getLastModifiedTime()));
        if (result.getN() == 0)
            throw new ModuleNotFoundException();
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
