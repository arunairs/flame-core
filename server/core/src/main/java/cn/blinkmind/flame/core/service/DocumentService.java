package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.repository.model.Document;
import cn.blinkmind.flame.repository.model.User;

import java.util.Optional;

public interface DocumentService {
    Optional<Document> get(final Long id, final User user);

    Document create(final Document document, final User user);
}
