package io.bayberry.core.service;

import io.bayberry.core.exception.Errors;
import io.bayberry.repository.ModuleRepository;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.entity.User;
import io.bayberry.repository.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final BranchService branchService;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, BranchService branchService) {
        this.moduleRepository = moduleRepository;
        this.branchService = branchService;
    }

    public Module create(Module module, Long branchId, User user) {
        if (!branchService.exists(branchId))
            throw Errors.BRANCH_IS_NOT_FOUND;

        Module output = new Module();
        output.setName(module.getName());
        output.setDescription(module.getDescription());
        output.setParent(module.getParent());
        output.setCreatedDateTime(LocalDateTime.now());
        output.setCreatorRef(user.getRef());
        try {
            moduleRepository.create(output, branchId);
        } catch (ResourceAlreadyExistsException exception) {
            throw Errors.MODULE_ALREADY_EXISTS;
        }
        return output;
    }
}
