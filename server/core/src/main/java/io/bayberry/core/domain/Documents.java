package io.bayberry.core.domain;

import io.bayberry.core.exception.Errors;
import io.bayberry.repository.DocumentRepository;
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
public class Documents {
    private final DocumentRepository documentRepository;
    private final Branches branches;

    @Autowired
    public Documents(DocumentRepository documentRepository, Branches branches) {
        this.documentRepository = documentRepository;
        this.branches = branches;
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

        this.branches.create("master", output.getId(), user);
        return output;
    }
}
