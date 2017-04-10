package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.core.service.ModuleService;
import io.bayberry.repository.entity.Module;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ModuleResource {

    private final ModuleService moduleService;

    @Autowired
    public ModuleResource(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Token
    @PostMapping(path = "branches/{branchId}/modules")
    public ResponseEntity<Module> create(@PathVariable(name = "branchId") Long branchId,
                                         @RequestBody Module module,
                                         @RequestAttribute(name = Attributes.USER) User user) {
        Module output = moduleService.create(module, branchId, user);
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
