package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.event.DocumentCreatedEvent;
import io.bayberry.core.event.EventPublisher;
import io.bayberry.core.exception.DocumentNotFoundException;
import io.bayberry.core.repository.DocumentRepository;
import io.bayberry.core.repository.entity.Document;
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

    public Document create(Document document, User user) {
        document.setCreatorId(user.getId());
        documentRepository.insert(document);
        eventPublisher.publish(new DocumentCreatedEvent(document));
        return document;
    }

    public Document get(Long id, User user) {
        return documentRepository.get(id).orElseThrow(DocumentNotFoundException::new);
    }

    public void update(Document document, User user) {
        document.setLastModifierId(user.getId());
        if (documentRepository.update(document) == 0) {
            throw new DocumentNotFoundException();
        }
    }
}
