package io.bayberry.core.resource;

import io.bayberry.core.bean.ObjectId;
import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.User;
import io.bayberry.core.domain.Branches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BranchResource {
    private final Branches branches;

    @Autowired
    public BranchResource(Branches branches) {
        this.branches = branches;
    }

    @Token
    @PostMapping(path = "documents/{documentId}/branches")
    public ResponseEntity<Branch> create(@PathVariable(name = "documentId") Long documentId,
                                         @RequestBody Branch branch,
                                         @RequestAttribute(name = Attributes.USER) User user) {
        Branch output = branches.create(branch, documentId, user);
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
        branches.update(id, branch, user);
        return ResponseEntity.ok().build();
    }

    @Token
    @DeleteMapping(path = "branches/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id,
                                       @RequestAttribute(name = Attributes.USER) User user) {
        branches.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}
