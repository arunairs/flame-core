package io.bayberry.core.service;

import io.bayberry.core.exception.Errors;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.entity.Branch;
import io.bayberry.repository.entity.Document;
import io.bayberry.repository.entity.Ref;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.bayberry.core.common.validation.Matcher.blank;
import static io.bayberry.core.common.validation.Matcher.not;
import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final BranchService branchService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, BranchService branchService) {
        this.documentRepository = documentRepository;
        this.branchService = branchService;
    }

    public Optional<Document> get(Long id, User user) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    public Document create(Document document, User user) {
        validateThat(document.getName(), not(blank()), orElseThrow(() -> Errors.DOCUMENT_NAME_IS_BLANK));

        Document output = new Document();
        output.setName(document.getName());
        output.setDescription(document.getDescription());
        output.setCreatorRef(new Ref<>(user.getId()));
        this.documentRepository.insert(output);

        this.branchService.create(Branch.builder().name("master").build(), output.getId(), user);
        return output;
    }
}