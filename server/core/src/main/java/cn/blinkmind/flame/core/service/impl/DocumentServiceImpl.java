package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.DocumentService;
import cn.blinkmind.flame.repository.DocumentRepository;
import cn.blinkmind.flame.repository.model.Document;
import cn.blinkmind.flame.repository.model.Ref;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static cn.blinkmind.flame.core.common.validation.Matcher.blank;
import static cn.blinkmind.flame.core.common.validation.Matcher.not;
import static cn.blinkmind.flame.core.common.validation.Validator.orElseThrow;
import static cn.blinkmind.flame.core.common.validation.Validator.validateThat;

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
    public Document create(final Document document, final User user) {
        validateThat(document.getName(), not(blank()), orElseThrow(Errors.DOCUMENT_NAME_IS_BLANK));

        Document output = new Document();
        output.setName(document.getName());
        output.setDescription(document.getDescription());
        output.setCreatorRef(new Ref<>(user.getId()));
        this.documentRepository.insert(output);
        return output;
    }
}
