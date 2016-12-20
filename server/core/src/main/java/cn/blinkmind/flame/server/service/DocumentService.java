package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.exception.Assertion;
import cn.blinkmind.flame.server.repository.entity.Document;
import cn.blinkmind.flame.server.repository.entity.DocumentType;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService extends PersistenceService
{
    @Autowired
    private DocumentRepository documentRepository;

    public Document get(long id, User user)
    {
        return documentRepository.get(id);
    }

    public Document require(long id, User user)
    {
        Document document = documentRepository.require(id);
        return document;
    }

    public Document create(Document document, User creator)
    {
        Assertion.notBlank(document.getName(), Errors.DOCUMENT_NAME_IS_BLANK);

        document.setId(newId());
        document.setType(DocumentType.REST_API);
        document.setCreator(creator);
        documentRepository.insert(document);
        return document;
    }
}