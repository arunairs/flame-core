package io.bayberry.core.service.impl;

import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.DocumentService;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.model.Document;
import io.bayberry.repository.model.Ref;
import io.bayberry.repository.model.User;
import io.bayberry.core.common.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.bayberry.core.common.validation.Matcher.blank;
import static io.bayberry.core.common.validation.Matcher.not;
import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Optional<Document> get(Long id, User user) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    @Override
    public Document create(Document document, User user) {
        Validator.validateThat(document.getName(), not(blank()), orElseThrow(Errors.DOCUMENT_NAME_IS_BLANK));

        Document output = new Document();
        output.setName(document.getName());
        output.setDescription(document.getDescription());
        output.setCreatorRef(new Ref<>(user.getId()));
        this.documentRepository.insert(output);
        return output;
    }

    @Override
    public boolean exists(Long id) {
        return documentRepository.exists(id);
    }
}
