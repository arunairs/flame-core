package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.domain.Documents;
import io.bayberry.repository.entity.Document;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "documents")
public class DocumentResource {
    private final Documents documents;

    @Autowired
    public DocumentResource(Documents documents) {
        this.documents = documents;
    }

    @Token
    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document,
                                           @RequestAttribute(name = Attributes.USER) User user) {
        Document output = documents.create(document, user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("{documentId}")
                .buildAndExpand(output.getId()).toUri())
                .body(output);
    }

    @Token
    @GetMapping(path = "{id}")
    public ResponseEntity<Document> get(@PathVariable(name = "id") Long id,
                                        @RequestAttribute(name = Attributes.USER) User user) {
        return ResponseEntity.ok(documents.get(id, user).orElseThrow(() -> Errors.RESOURCE_NOT_FOUND));
    }
}
