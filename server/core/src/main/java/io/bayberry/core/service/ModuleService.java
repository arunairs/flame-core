package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.common.Error;
import io.bayberry.core.common.Result;
import io.bayberry.repository.ModuleRepository;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.exception.BranchNotFoundException;
import io.bayberry.repository.exception.ModuleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Result<Module, Error> create(Module module, User user) {
        module.setCreatorId(user.getId());
        try {
            return Result.ok(moduleRepository.insert(module));
        } catch (BranchNotFoundException e) {
            return Result.fail(Error.BRANCH_NOT_FOUND);
        } catch (ModuleNotFoundException e) {
            return Result.fail(Error.MODULE_NOT_FOUND);
        }
    }

    public Result<Module, Error> update(Module module, User user) {
        try {
            return Result.failIfNull(moduleRepository.update(module), Error.MODULE_NOT_FOUND);
        } catch (ModuleNotFoundException e) {
            return Result.fail(Error.MODULE_NOT_FOUND);
        }
    }

    public void delete(Long branchId, Long id, User user) {
        moduleRepository.delete(branchId, id);
    }
}
