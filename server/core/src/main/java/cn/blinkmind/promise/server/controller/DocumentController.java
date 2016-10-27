package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.DocumentRepository;
import cn.blinkmind.promise.server.annotation.Token;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 27/09/2016 12:04 AM
 */
@RestController
@RequestMapping(path = "documents")
public class DocumentController
{
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentService documentService;

	@Token
	@GetMapping(value = "/{id}")
	public ResponseEntity<Document> get(@PathVariable(name = "id") long id, @RequestAttribute User user)
	{
		return ResponseEntity.ok(documentRepository.require(id));
	}

	@Token
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ObjectId> create(@RequestBody Document documentData, @RequestAttribute User user)
	{
		Document document = documentService.create(documentData, user);
		return ResponseEntity.ok(new ObjectId(document.getId()));
	}
}
