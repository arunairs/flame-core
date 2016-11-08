package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.DocumentRepository;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.DocumentType;
import cn.blinkmind.promise.server.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
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
		if (StringUtils.isBlank(document.getName()))
			Error.occurs(Errors.DOCUMENT_NAME_IS_BLANK);

		document.clean();
		document.setId(repositoryService.newId());
		document.setType(DocumentType.REST_API);
		document.setCreator(creator);
		document.refreshCreatedDate();
		documentRepository.insert(document);
		return document;
	}
}
