package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.exception.ApiNotFoundException;
import io.bayberry.core.exception.BranchNotFoundException;
import io.bayberry.core.exception.ModuleNotFoundException;
import io.bayberry.core.repository.ApiRepository;
import io.bayberry.core.repository.ModuleRepository;
import io.bayberry.core.repository.entity.Api;
import io.bayberry.core.repository.exception.EntityNotFoundException;
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
        try {
            apiRepository.insert(api);
            return api;
        } catch (EntityNotFoundException e) {
            if (moduleRepository.get(api.getBranchId(), api.getModuleId()).isPresent()) {
                throw new BranchNotFoundException();
            } else {
                throw new ModuleNotFoundException();
            }
        }
    }

    public Api get(Long branchId, Long id, User user) {
        return apiRepository.get(branchId, id).orElseThrow(ApiNotFoundException::new);
    }

    public void update(Api api, User user) {
        api.setLastModifierId(user.getId());
        try {
            apiRepository.update(api);
        } catch (EntityNotFoundException e) {
            throw new ApiNotFoundException();
        }
    }

    public void delete(Long branchId, Long id, User user) {
        apiRepository.delete(branchId, id);
    }
}
