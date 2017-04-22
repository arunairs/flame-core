package io.bayberry.core.service;

import io.bayberry.core.authentication.Auth;
import io.bayberry.core.event.EventPublisher;
import io.bayberry.core.event.DocumentCreatedEvent;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.model.Branch;
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

    public Optional<Document> get(Long id, Auth auth) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    public Document create(Document document, Auth auth) {
        document.setCreatorId(auth.getUserId());
        this.documentRepository.insert(document);
        this.eventPublisher.publish(new DocumentCreatedEvent(document, auth));
        return document;
    }
}
