package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.DocumentService;
import io.bayberry.repository.model.Document;
import io.bayberry.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "documents")
public class DocumentResource extends AbstractResource {
    private DocumentService documentService;

    @Autowired
    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Token
    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document,
                                           @RequestAttribute(name = Attributes.USER) User user) {
        Document output = documentService.create(document, user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("{documentId}")
                .buildAndExpand(output.getId()).toUri())
                .body(output);
    }

    @Token
    @GetMapping(path = "{documentId}")
    public ResponseEntity<Document> get(@PathVariable(name = "documentId") Long documentId,
                                        @RequestAttribute(name = Attributes.USER) User user) {
        return ResponseEntity.ok(documentService.get(documentId, user).orElseThrow(() -> Errors.RESOURCE_NOT_FOUND));
    }
}
