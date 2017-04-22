package io.bayberry.core.controller;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.core.service.ModuleService;
import io.bayberry.repository.model.Module;
import io.bayberry.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ModuleController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Token
    @PostMapping(path = "branches/{branchId}/modules")
    public ResponseEntity<Module> create(@PathVariable(name = "branchId") Long branchId,
                                         @RequestBody Module moduleEntity,
                                         @RequestAttribute(name = Attributes.AUTH) User userEntity) {
        Module output = moduleService.create(moduleEntity, branchId, userEntity);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("branches/{branchId}/modules/{moduleId}")
                .buildAndExpand(branchId, output.getId()).toUri())
                .body(output);
    }

//    @Token
//    @PutMapping(path = "branches/{branchId}/modules/{moduleId}")
//    public ResponseEntity<Module> updateAndReturn(@PathVariable(name = "branchId") Long branchId,
//                                         @PathVariable(name = "moduleId") Long moduleId,
//                                         @RequestBody Module module) {
//
//    }
//
//    @Token
//    @DeleteMapping(path = "branches/{branchId}/modules/{moduleId}")
//    public ResponseEntity<Void> delete(@PathVariable(name = "branchId") Long branchId,
//                                       @PathVariable(name = "moduleId") Long moduleId) {
//
//    }
}
