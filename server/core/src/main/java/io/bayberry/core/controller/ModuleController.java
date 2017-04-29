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
    public ModuleResponse create(@PathVariable(name = "branchId") Long branchId,
                                 @Valid @RequestBody ModuleRequest request,
                                 User user) {
        request.setBranchId(branchId);
        return new ModuleResponse(moduleService.create(moduleConverter.convert(request), user).orElse(error -> {
            switch (error) {
                case BRANCH_NOT_FOUND:
                    throw new ResourceNotFoundException(error);
            }
        }));
    }

    @Token
    @PostMapping(path = "branches/{branchId}/archive/modules/{parentId}")
    public ModuleResponse createSubModule(@PathVariable(name = "branchId") Long branchId,
                                          @PathVariable(name = "parentId") Long parentId,
                                          @Valid @RequestBody ModuleRequest request,
                                          User user) {
        request.setBranchId(branchId);
        request.setParentId(parentId);
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
    @PutMapping(path = "branches/{branchId}/archive/modules/{moduleId}")
    public ModuleResponse update(@PathVariable(name = "branchId") Long branchId,
                                 @PathVariable(name = "moduleId") Long moduleId,
                                 @Valid @RequestBody ModuleRequest request,
                                 User user) {
        request.setId(moduleId);
        request.setBranchId(branchId);
        return new ModuleResponse(moduleService.update(moduleConverter.convert(request), user).orElse(error -> {
            throw new ResourceNotFoundException(error);
        }));
    }

    @Token
    @DeleteMapping(path = "branches/{branchId}/archive/modules/{moduleId}")
    public void delete(@PathVariable(name = "branchId") Long branchId,
                       @PathVariable(name = "moduleId") Long moduleId,
                       User user) {
        moduleService.delete(moduleId, branchId, user);
    }
}
