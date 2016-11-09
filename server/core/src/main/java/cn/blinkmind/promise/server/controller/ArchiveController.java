package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.ArchiveRepository;
import cn.blinkmind.promise.server.repository.DocumentRepository;
import cn.blinkmind.promise.server.repository.entity.Archive;
import cn.blinkmind.promise.server.annotation.Token;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author jiaan.zhang@oracle.com
 * @date 17/10/2016 12:32 AM
 */
@RestController
public class ArchiveController
{
	@Autowired
	private ArchiveRepository archiveRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ArchiveService archiveService;

	@Token
	@PostMapping(path = "documents/{documentId}/archives")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ObjectId> create(@PathVariable(name = "documentId") long documentId, @RequestBody Archive archiveData, @RequestAttribute User user)
	{
		Document document = documentRepository.require(documentId);
		Archive archive = archiveService.fillAndPersist(archiveData, document, user);
		return ResponseEntity.ok(new ObjectId(archive.getId()));
	}

	@GetMapping(path = "archives/{id}")
	public ResponseEntity<Archive> get(@PathVariable(name = "id") long id)
	{
		return ResponseEntity.ok(archiveRepository.findOne(id));
	}

	@Token
	@PatchMapping(path = "archives/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> patch, @RequestAttribute User user)
	{
		Archive archive = archiveRepository.require(id);
		archiveService.update(archive, patch, user);
		return ResponseEntity.ok().build();
	}
}
