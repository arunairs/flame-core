package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.common.Result;
import io.bayberry.core.common.Error;
import io.bayberry.repository.BranchRepository;
import io.bayberry.repository.ModuleRepository;
import io.bayberry.repository.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, BranchRepository branchRepository) {
        this.moduleRepository = moduleRepository;
        this.branchRepository = branchRepository;
    }

    public Result<Module, Error> create(Module module, Long branchId, User user) {
        module.setCreatorId(user.getId());
        if (moduleRepository.create(module, branchId) == null) {
            if (!branchRepository.exists(branchId))
                return Result.fail(Error.BRANCH_NOT_FOUND);
            else
                return Result.fail(Error.MODULE_NOT_FOUND);
        }
        return Result.ok(module);
    }

    public Result<Module, Error> update(Module module, Long branchId, User user) {
        return Result.failIfNull(moduleRepository.update(module, branchId), Error.MODULE_NOT_FOUND);
    }

    public void delete(Long moduleId, Long branchId, User user) {
        moduleRepository.delete(moduleId, branchId);
    }
}
