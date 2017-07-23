package io.bayberry.flame.core.service;

import io.bayberry.flame.core.authentication.User;
import io.bayberry.flame.core.exception.ApiNotFoundException;
import io.bayberry.flame.core.exception.BranchNotFoundException;
import io.bayberry.flame.core.exception.ModuleNotFoundException;
import io.bayberry.flame.core.repository.ApiRepository;
import io.bayberry.flame.core.repository.ModuleRepository;
import io.bayberry.flame.core.repository.entity.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final ApiRepository apiRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public ApiService(ApiRepository apiRepository, ModuleRepository moduleRepository) {
        this.apiRepository = apiRepository;
        this.moduleRepository = moduleRepository;
    }

    public Api create(Api api, User user) {
        api.setCreatorId(user.getId());
        return apiRepository.insert(api).orElseGet(() -> {
            if (moduleRepository.get(api.getBranchId(), api.getModuleId()).isPresent()) {
                throw new BranchNotFoundException();
            } else {
                throw new ModuleNotFoundException();
            }
        });
    }

    public Api get(Long branchId, Long id, User user) {
        return apiRepository.get(branchId, id).orElseThrow(ApiNotFoundException::new);
    }

    public void update(Api api, User user) {
        api.setLastModifierId(user.getId());
        if (apiRepository.update(api) == 0) {
            throw new ApiNotFoundException();
        }
    }

    public void delete(Long branchId, Long id, User user) {
        apiRepository.delete(branchId, id);
    }
}
