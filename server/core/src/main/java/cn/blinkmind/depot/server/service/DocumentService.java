package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.entity.CRUD;
import cn.blinkmind.depot.server.repository.entity.Document;
import cn.blinkmind.depot.server.repository.entity.DocumentType;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.exception.Assertion;
import cn.blinkmind.depot.server.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@outlook.com
 * @date 13/10/2016 11:51 PM
 */
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
