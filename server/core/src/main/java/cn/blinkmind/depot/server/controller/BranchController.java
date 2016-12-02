package cn.blinkmind.depot.server.controller;

import cn.blinkmind.depot.server.annotation.Token;
import cn.blinkmind.depot.server.repository.entity.Document;
import cn.blinkmind.depot.server.bean.web.ObjectId;
import cn.blinkmind.depot.server.repository.BranchRepository;
import cn.blinkmind.depot.server.repository.DocumentRepository;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 25/11/2016 1:05 PM
 */
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
		Branch branch = branchRepository.require(id);
		branchService.delete(branch, user);
		return ResponseEntity.noContent().build();
	}
}
