package cn.blinkmind.duck.server.service;

import cn.blinkmind.duck.server.exception.Errors;
import cn.blinkmind.duck.server.exception.Assertion;
import cn.blinkmind.duck.server.repository.entity.Document;
import cn.blinkmind.duck.server.repository.entity.DocumentType;
import cn.blinkmind.duck.server.repository.entity.User;
import cn.blinkmind.duck.server.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RepositoryService repositoryService;

    public Document get(long id, User user) {
        return documentRepository.get(id);
    }

    public Document require(long id, User user) {
        Document document = documentRepository.require(id);
        return document;
    }

    public Document create(Document document, User creator) {
        Assertion.notBlank(document.getName(), Errors.DOCUMENT_NAME_IS_BLANK);

        document.setId(repositoryService.newId());
        document.setType(DocumentType.REST_API);
        document.setCreator(creator);
        documentRepository.insert(document);
        return document;
    }
}
