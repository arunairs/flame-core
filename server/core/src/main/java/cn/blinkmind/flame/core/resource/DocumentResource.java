package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.DocumentService;
import cn.blinkmind.flame.repository.model.Document;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "documents")
public class DocumentResource extends AbstractResource {
    private final DocumentService documentService;

    @Autowired
    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Token
    @PostMapping
    public ResponseEntity<Document> create(@RequestBody final Document input,
                                           @RequestAttribute(name = Attributes.USER) final User user) {
        Document document = documentService.create(input, user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{documentId}")
                .buildAndExpand(document.getId()).toUri())
                .body(document);
    }

    @Token
    @GetMapping(path = "{documentId}")
    public ResponseEntity<Document> get(@PathVariable(name = "documentId") final Long documentId,
                                        @RequestAttribute(name = Attributes.USER) final User user) {
        return ResponseEntity.ok(documentService.get(documentId, user).orElseThrow(() -> Errors.RESOURCE_NOT_FOUND));
    }
}
