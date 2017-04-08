package io.bayberry.core.resource;

import io.bayberry.core.bean.ObjectId;
import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Module;
import io.bayberry.repository.model.User;
import io.bayberry.core.domain.Branches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BranchResource extends AbstractResource {
    private final Branches branches;

    @Autowired
    public BranchResource(Branches branches) {
        this.branches = branches;
    }

    @Token
    @PostMapping(path = "documents/{id}/branches")
    public ResponseEntity<Branch> create(@PathVariable(name = "id") Long id,
                                         @RequestBody Branch branch,
                                         @RequestAttribute(name = Attributes.USER) User user) {
        Branch output = branches.create(branch, id, user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("branches/{id}")
                .buildAndExpand(output.getId()).toUri())
                .body(output);
    }

    @Token
    @PutMapping(path = "branches/{id}")
    public ResponseEntity<ObjectId> update(@PathVariable(name = "id") Long id,
                                           @RequestBody Branch branch,
                                           @RequestAttribute(name = Attributes.USER) User user) {
        branches.updateProfile(id, branch, user);
        return ResponseEntity.ok().build();
    }

    @Token
    @DeleteMapping(path = "branches/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id,
                                       @RequestAttribute(name = Attributes.USER) User user) {
        branches.delete(id, user);
        return ResponseEntity.noContent().build();
    }

    @Token
    @PostMapping(path = "branches/{id}/modules")
    public ResponseEntity<Branch> createModule(@PathVariable(name = "id") Long id,
                                               @RequestBody Module module,
                                               @RequestAttribute(name = Attributes.USER) User user) {
        Branch output = branches.create(branch, documentId, user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("branches/{branchId}")
                .buildAndExpand(output.getId()).toUri())
                .body(output);
    }
}
