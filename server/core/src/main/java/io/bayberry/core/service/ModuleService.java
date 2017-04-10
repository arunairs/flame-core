package io.bayberry.core.service;

import io.bayberry.core.exception.Errors;
import io.bayberry.repository.ModuleRepository;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.entity.User;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final BranchService branchService;
    private final IdGenerator<Long> idGenerator;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, BranchService branchService, IdGenerator<Long> idGenerator) {
        this.moduleRepository = moduleRepository;
        this.branchService = branchService;
        this.idGenerator = idGenerator;
    }

    public Module create(Module module, Long branchId, User user) {
        if (!branchService.exists(branchId))
            throw Errors.BRANCH_IS_NOT_FOUND;

        Module output = new Module();
        output.setId(idGenerator.nextId());
        output.setName(module.getName());
        output.setDescription(module.getDescription());
        output.setParent(module.getParent());
        output.setCreatedDateTime(LocalDateTime.now());
        output.setCreatorRef(user.getRef());

        //TODO 插入该module的id到archive的moduleOrder中
        moduleRepository.create(output, branchId);
        return output;
    }
}
