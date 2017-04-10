package io.bayberry.repository;

import com.mongodb.WriteResult;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.exception.ResourceAlreadyExistsException;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static io.bayberry.repository.query.Keys.*;

@Repository
public class ModuleRepository extends AbstractMongoRepository<Branch, Long> {
    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleRepository(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Module create(Module module, Long branchId) {
        module.setId(idGenerator.nextId());
        WriteResult result = super.update(Query.query(Criteria.where(ID).is(branchId).and(MODULES_NAME).ne(module.getName())),
                new Update().push(MODULES, module));
        if (result.getN() == 0) {
            throw new ResourceAlreadyExistsException();
        }
        super.update(Query.query(Criteria.where(ID).is(branchId).and(MODULE_ORDER_NAME).ne(module.getName())),
                new Update().push(MODULE_ORDER, module.getRef()));
        return module;
    }
}
