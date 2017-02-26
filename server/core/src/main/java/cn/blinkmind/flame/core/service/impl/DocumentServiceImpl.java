package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.DocumentService;
import cn.blinkmind.flame.repository.DocumentRepository;
import cn.blinkmind.flame.repository.model.Document;
import cn.blinkmind.flame.repository.model.Ref;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Optional<Document> get(final Long id, final User user) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    @Override
    public Document create(final Document input, final User user) {
        Assert.isNotBlank(input.getName(), Errors.DOCUMENT_NAME_IS_BLANK);

        Document document = new Document();
        document.setName(input.getName());
        document.setDescription(input.getDescription());
        document.setCreatorRef(new Ref<>(user.getId()));
        this.documentRepository.insert(document);
        return document;
    }
}
