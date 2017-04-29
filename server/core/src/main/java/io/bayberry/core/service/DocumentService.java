package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.common.Error;
import io.bayberry.core.common.Result;
import io.bayberry.core.event.DocumentCreatedEvent;
import io.bayberry.core.event.EventPublisher;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final EventPublisher eventPublisher;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, EventPublisher eventPublisher) {
        this.documentRepository = documentRepository;
        this.eventPublisher = eventPublisher;
    }

    public Result<Document, Error> create(Document document, User user) {
        document.setCreatorId(user.getId());
        documentRepository.insert(document);
        eventPublisher.publish(new DocumentCreatedEvent(document));
        return Result.ok(document);
    }

    public Result<Document, Error> get(Long id, User user) {
        Document document = documentRepository.get(id);
        return Result.failIfNull(document, Error.DOCUMENT_NOT_FOUND);
    }

    public Result<Document, Error> update(Document document, User user) {
        return Result.failIfNull(documentRepository.updateAndReturn(document), Error.DOCUMENT_NOT_FOUND);
    }
}
