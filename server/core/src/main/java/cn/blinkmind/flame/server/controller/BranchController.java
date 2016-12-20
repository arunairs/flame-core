package cn.blinkmind.flame.server.controller;

import cn.blinkmind.flame.server.annotation.Token;
import cn.blinkmind.flame.server.bean.web.ObjectId;
import cn.blinkmind.flame.server.repository.entity.Document;
import cn.blinkmind.flame.server.repository.BranchRepository;
import cn.blinkmind.flame.server.repository.DocumentRepository;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BranchController
{
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private BranchService branchService;

    @Token
    @PostMapping(path = "documents/{documentId}/branches")
    public ResponseEntity<ObjectId> create(@PathVariable(name = "documentId") long documentId, @RequestBody Branch branch, @RequestAttribute(name = "user") User user)
    {
        Document document = documentRepository.require(documentId);
        branchService.create(branch, document, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(branch.getId()));
    }

    @Token
    @DeleteMapping(path = "branches/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id, @RequestAttribute(name = "user") User user)
    {
        branchService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}
