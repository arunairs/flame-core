package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Assertion;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.DocumentRepository;
import cn.blinkmind.promise.server.repository.entity.CRUD;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.DocumentType;
import cn.blinkmind.promise.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@oracle.com
 * @date 13/10/2016 11:51 PM
 */
@Service
public class DocumentService
{
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private RepositoryService repositoryService;

	public Document create(Document document, User creator)
	{
		Assertion.notBlank(document.getName(), Errors.DOCUMENT_NAME_IS_BLANK);

		document.cleanup(CRUD.CREATE);
		document.setId(repositoryService.newId());
		document.setType(DocumentType.REST_API);
		document.setCreator(creator);
		document.refreshCreatedDate();
		documentRepository.insert(document);
		return document;
	}
}
