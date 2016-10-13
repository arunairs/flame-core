package cn.blm.promise.server.controller;

import cn.blm.promise.server.annotation.Token;
import cn.blm.promise.server.bean.BodyWrapper;
import cn.blm.promise.server.exception.BadRequestException;
import cn.blm.promise.server.exception.Errors;
import cn.blm.promise.server.repository.ArchiveRepository;
import cn.blm.promise.server.repository.DocumentRepository;
import cn.blm.promise.server.repository.entity.Document;
import cn.blm.promise.server.repository.entity.User;
import cn.blm.promise.server.repository.enumeration.DocumentType;
import cn.blm.promise.server.service.DocumentService;
import cn.blm.promise.server.service.RepositoryService;
import org.apache.commons.lang3.StringUtils;
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
	public ResponseEntity<Document> get(@PathVariable("id") long id, @RequestAttribute User user)
	{
		return ResponseEntity.ok(documentRepository.findOne(id));
	}

	@Token
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BodyWrapper> create(@RequestBody Document documentData, @RequestAttribute User user)
	{
		Document document = documentService.create(documentData, user);
		return ResponseEntity.ok(BodyWrapper.builder().id(document.getId()).build());
	}
}
