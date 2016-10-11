package cn.blm.promise.server.controller;

import cn.blm.promise.server.annotation.Token;
import cn.blm.promise.server.exception.BadRequestException;
import cn.blm.promise.server.exception.Errors;
import cn.blm.promise.server.repository.ApiRepository;
import cn.blm.promise.server.repository.ArchiveRepository;
import cn.blm.promise.server.repository.DocumentRepository;
import cn.blm.promise.server.repository.entity.Api;
import cn.blm.promise.server.repository.entity.Document;
import cn.blm.promise.server.repository.entity.User;
import cn.blm.promise.server.repository.enumeration.DocumentType;
import cn.blm.promise.server.service.IdService;
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
	ApiRepository apiRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	ArchiveRepository archiveRepository;

	@Autowired
	IdService idService;

	@Token
	@GetMapping(path = "doc")
	public Api doc(@RequestAttribute(required = false) User user)
	{
		if (user == null) throw new RuntimeException();
		Api api = new Api();
		api.setId(idService.newId());
		api.setName("test api xxxxxx");
		apiRepository.save(api);
		return apiRepository.findOne(api.getId());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Object create(@RequestBody Document document)
	{
		if (StringUtils.isBlank(document.getName()))
			throw new BadRequestException(Errors.DOCUMENT_NAME_IS_BLANK);
		document.setId(idService.newId());
		document.setDocumentType(DocumentType.REST_API);
		documentRepository.insert(document);
		return ResponseEntity.ok(document.getId());
	}
}
