package io.bayberry.flame.core.service;

import io.bayberry.flame.core.authentication.User;
import io.bayberry.flame.core.exception.BranchNotFoundException;
import io.bayberry.flame.core.exception.ModuleNotFoundException;
import io.bayberry.flame.core.repository.ModuleRepository;
import io.bayberry.flame.core.repository.entity.Module;
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
        return moduleRepository.insert(module).orElseGet(() -> {
            if (module.hasParent()) {
                throw new ModuleNotFoundException();
            } else {
                throw new BranchNotFoundException();
            }
        });
    }

    public void update(Module module, User user) {
        module.setLastModifierId(user.getId());
        if (moduleRepository.update(module) == 0) {
            throw new ModuleNotFoundException();
        }
    }

    public void delete(Long branchId, Long id, User user) {
        moduleRepository.delete(branchId, id);
    }
}
