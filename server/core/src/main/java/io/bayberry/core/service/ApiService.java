package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.common.Error;
import io.bayberry.core.common.Result;
import io.bayberry.repository.ApiRepository;
import io.bayberry.repository.entity.Api;
import io.bayberry.repository.exception.ApiNotFoundException;
import io.bayberry.repository.exception.BranchNotFoundException;
import io.bayberry.repository.exception.ModuleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final ApiRepository apiRepository;

    @Autowired
    public ApiService(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public Result<Api, Error> create(Api api, User user) {
        api.setCreatorId(user.getId());
        try {
            apiRepository.insert(api);
            return Result.ok(api);
        } catch (BranchNotFoundException e) {
            return Result.fail(Error.BRANCH_NOT_FOUND);
        } catch (ModuleNotFoundException e) {
            return Result.fail(Error.MODULE_NOT_FOUND);
        }
    }

    public Result<Api, Error> update(Api api, User user) {
        api.setCreatorId(user.getId());
        try {
            return Result.ok(apiRepository.update(api));
        } catch (ApiNotFoundException e) {
            return Result.fail(Error.API_NOT_FOUND);
        }
    }

    public void delete(Long branchId, Long id, User user) {
        apiRepository.delete(branchId, id);
    }
}
