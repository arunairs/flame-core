package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.event.DocumentCreatedEvent;
import io.bayberry.core.event.EventPublisher;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final EventPublisher eventPublisher;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, EventPublisher eventPublisher) {
        this.documentRepository = documentRepository;
        this.eventPublisher = eventPublisher;
    }

    public Optional<Document> get(Long id, User user) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    public Document create(Document document, User user) {
        document.setCreatorId(user.getId());
        documentRepository.insert(document);
        eventPublisher.publish(new DocumentCreatedEvent(document));
        return document;
    }
}
