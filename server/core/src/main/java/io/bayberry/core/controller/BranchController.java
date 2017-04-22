package io.bayberry.core.controller;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.authentication.User;
import io.bayberry.core.dto.BranchRequest;
import io.bayberry.core.dto.BranchResponse;
import io.bayberry.core.dto.converter.BranchConverter;
import io.bayberry.core.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BranchController {

    private final BranchConverter branchConverter;
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchConverter branchConverter, BranchService branchService) {
        this.branchConverter = branchConverter;
        this.branchService = branchService;
    }

    @Token
    @PostMapping(path = "documents/{documentId}/branches")
    public BranchResponse create(@PathVariable(name = "documentId") Long documentId,
                                 @Valid @RequestBody BranchRequest request,
                                 User user) {
        return new BranchResponse(branchService.create(branchConverter.convert(request), documentId, user));
    }

    @Token
    @PutMapping(path = "branches/{id}")
    public BranchResponse update(@PathVariable(name = "id") Long id,
                                 @Valid @RequestBody BranchRequest request,
                                 User user) {
        request.setId(id);
        return new BranchResponse(branchService.update(branchConverter.convert(request), user));
    }

    @Token
    @DeleteMapping(path = "branches/{id}")
    public void delete(@PathVariable(name = "id") Long id, User user) {
        branchService.delete(id, user);
    }
}
