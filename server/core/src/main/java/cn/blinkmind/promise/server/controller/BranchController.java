package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.annotation.Token;
import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.DocumentRepository;
import cn.blinkmind.promise.server.repository.entity.Branch;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.BranchService;
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
	private DocumentRepository documentRepository;

	@Autowired
	private BranchService branchService;

	@Token
	@PostMapping(path = "documents/{documentId}/branches")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ObjectId> create(@PathVariable(name = "documentId") long documentId, @RequestBody Branch branch, @RequestAttribute User user)
	{
		Document document = documentRepository.require(documentId);
		branchService.create(branch, document, user);
		return ResponseEntity.ok(new ObjectId(branch.getId()));
	}
}
