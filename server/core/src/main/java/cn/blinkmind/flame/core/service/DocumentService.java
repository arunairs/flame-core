package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.repository.model.Document;
import cn.blinkmind.flame.repository.model.User;

import java.util.Optional;

public interface DocumentService {
    Optional<Document> get(Long id, User user);

    Document create(Document document, User user);
}
