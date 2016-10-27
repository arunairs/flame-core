package cn.blm.promise.server.service;

import cn.blm.promise.server.exception.InvalidRequestException;
import cn.blm.promise.server.exception.Errors;
import cn.blm.promise.server.repository.DocumentRepository;
import cn.blm.promise.server.repository.entity.Document;
import cn.blm.promise.server.repository.entity.User;
import cn.blm.promise.server.repository.entity.DocumentType;
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

	public Document create(Document documentData, User creator)
	{
		if (StringUtils.isBlank(documentData.getName()))
			throw new InvalidRequestException(Errors.DOCUMENT_NAME_IS_BLANK);

		Document document = new Document();
		document.setId(repositoryService.newId());
		document.setName(documentData.getName());
		document.setDocumentType(DocumentType.REST_API);
		document.setCreator(creator);
		document.writeCreatedDate();
		documentRepository.insert(document);
		return document;
	}
}
