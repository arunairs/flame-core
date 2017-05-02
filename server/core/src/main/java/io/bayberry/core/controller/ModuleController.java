package io.bayberry.core.controller;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.authentication.User;
import io.bayberry.core.dto.ModuleRequest;
import io.bayberry.core.dto.ModuleResponse;
import io.bayberry.core.dto.converter.ModuleConverter;
import io.bayberry.core.exception.ResourceNotFoundException;
import io.bayberry.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ModuleController {

    private final ModuleConverter moduleConverter;
    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleConverter moduleConverter, ModuleService moduleService) {
        this.moduleConverter = moduleConverter;
        this.moduleService = moduleService;
    }

    @Token
    @PostMapping(path = "branches/{branchId}/archive/modules")
    public ModuleResponse create(@Valid @RequestBody ModuleRequest request,
                                 User user) {
        return new ModuleResponse(moduleService.create(moduleConverter.convert(request), user).orElse(error -> {
            switch (error) {
                case BRANCH_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
            }
        }));
    }

    @Token
    @PostMapping(path = "branches/{branchId}/archive/modules/{parentId}")
    public ModuleResponse createSubModule(@Valid @RequestBody ModuleRequest request,
                                          User user) {
        return new ModuleResponse(moduleService.create(moduleConverter.convert(request), user).orElse(error -> {
            switch (error) {
                case BRANCH_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
                case MODULE_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
            }
        }));
    }

    @Token
    @PutMapping(path = "branches/{branchId}/archive/modules/{id}")
    public ModuleResponse update(@Valid @RequestBody ModuleRequest request,
                                 User user) {
        return new ModuleResponse(moduleService.update(moduleConverter.convert(request), user).orElse(error -> {
            throw new ResourceNotFoundException(error);
        }));
    }

    @Token
    @DeleteMapping(path = "branches/{branchId}/archive/modules/{id}")
    public void delete(@PathVariable(name = "branchId") Long branchId,
                       @PathVariable(name = "id") Long id,
                       User user) {
        moduleService.delete(branchId, id, user);
    }
}
