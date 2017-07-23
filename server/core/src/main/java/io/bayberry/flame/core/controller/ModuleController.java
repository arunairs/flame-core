package io.bayberry.flame.core.controller;

import io.bayberry.flame.core.annotation.Token;
import io.bayberry.flame.core.authentication.User;
import io.bayberry.flame.core.dto.ModuleRequest;
import io.bayberry.flame.core.dto.ModuleResponse;
import io.bayberry.flame.core.dto.converter.ModuleConverter;
import io.bayberry.flame.core.service.ModuleService;
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
        return new ModuleResponse(moduleService.create(moduleConverter.convert(request), user));
    }

    @Token
    @PostMapping(path = "branches/{branchId}/archive/modules/{parentId}")
    public ModuleResponse createSubModule(@Valid @RequestBody ModuleRequest request,
                                          User user) {
        return new ModuleResponse(moduleService.create(moduleConverter.convert(request), user));
    }

    @Token
    @PutMapping(path = "branches/{branchId}/archive/modules/{id}")
    public void update(@Valid @RequestBody ModuleRequest request,
                       User user) {
        moduleService.update(moduleConverter.convert(request), user);
    }

    @Token
    @DeleteMapping(path = "branches/{branchId}/archive/modules/{id}")
    public void delete(@PathVariable(name = "branchId") Long branchId,
                       @PathVariable(name = "id") Long id,
                       User user) {
        moduleService.delete(branchId, id, user);
    }
}
