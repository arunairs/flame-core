package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.exception.BranchNotFoundException;
import io.bayberry.core.exception.ModuleNotFoundException;
import io.bayberry.core.repository.ModuleRepository;
import io.bayberry.core.repository.entity.Module;
import io.bayberry.core.repository.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Module create(Module module, User user) {
        module.setCreatorId(user.getId());
        try {
            return moduleRepository.insert(module);
        } catch (EntityNotFoundException e) {
            if (module.hasParent()) {
                throw new ModuleNotFoundException();
            } else {
                throw new BranchNotFoundException();
            }
        }
    }

    public void update(Module module, User user) {
        module.setLastModifierId(user.getId());
        try {
            moduleRepository.update(module);
        } catch (EntityNotFoundException e) {
            throw new ModuleNotFoundException();
        }
    }

    public void delete(Long branchId, Long id, User user) {
        moduleRepository.delete(branchId, id);
    }
}
