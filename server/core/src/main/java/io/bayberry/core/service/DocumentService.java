package io.bayberry.core.service;

import io.bayberry.repository.model.Document;
import io.bayberry.repository.model.User;

import java.util.Optional;

public interface DocumentService {
    Optional<Document> get(Long id, User user);

    Document create(Document document, User user);
}
