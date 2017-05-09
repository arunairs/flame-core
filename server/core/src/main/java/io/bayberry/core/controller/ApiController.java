package io.bayberry.core.controller;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.authentication.User;
import io.bayberry.core.dto.ApiRequest;
import io.bayberry.core.dto.ApiResponse;
import io.bayberry.core.dto.converter.ApiConverter;
import io.bayberry.core.dto.factory.ApiResponseFactory;
import io.bayberry.core.exception.ResourceNotFoundException;
import io.bayberry.core.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiController {

    private final ApiConverter apiConverter;
    private final ApiResponseFactory apiResponseFactory;
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiConverter apiConverter, ApiResponseFactory apiResponseFactory, ApiService apiService) {
        this.apiConverter = apiConverter;
        this.apiResponseFactory = apiResponseFactory;
        this.apiService = apiService;
    }

    @Token
    @PostMapping("branches/{branchId}/archive/modules/{moduleId}/apis")
    public ApiResponse create(@Valid @RequestBody ApiRequest request,
                              User user) {
        return apiResponseFactory.createFrom(apiService.create(apiConverter.convert(request), user).orElse(error -> {
            switch (error) {
                case MODULE_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
                case BRANCH_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
            }
        }));
    }

    @Token
    @PutMapping("branches/{branchId}/archive/apis/{id}")
    public ApiResponse update(@Valid @RequestBody ApiRequest request,
                              User user) {
        return apiResponseFactory.createFrom(apiService.update(apiConverter.convert(request), user).orElse(error -> {
            switch (error) {
                case API_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
            }
        }));
    }

    @Token
    @DeleteMapping("branches/{branchId}/archive/apis/{id}")
    public void delete(@PathVariable Long branchId,
                       @PathVariable Long id,
                       User user) {
        apiService.delete(branchId, id, user);
    }
}
