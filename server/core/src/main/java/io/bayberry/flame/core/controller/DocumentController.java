package io.bayberry.flame.core.controller;

import io.bayberry.flame.core.annotation.Token;
import io.bayberry.flame.core.authentication.User;
import io.bayberry.flame.core.dto.DocumentRequest;
import io.bayberry.flame.core.dto.DocumentResponse;
import io.bayberry.flame.core.dto.converter.DocumentConverter;
import io.bayberry.flame.core.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "documents")
public class DocumentController {

    private final DocumentConverter documentConverter;
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentConverter documentConverter, DocumentService documentService) {
        this.documentConverter = documentConverter;
        this.documentService = documentService;
    }

    @Token
    @PostMapping
    public DocumentResponse create(@Valid @RequestBody DocumentRequest request, User user) {
        return new DocumentResponse(documentService.create(documentConverter.convert(request), user));
    }

    @Token
    @GetMapping(path = "{id}")
    public DocumentResponse get(@PathVariable(name = "id") Long id, User user) {
        return new DocumentResponse(documentService.get(id, user));
    }

    @Token
    @PutMapping(path = "{id}")
    public void update(@Valid @RequestBody DocumentRequest request,
                       User user) {
        documentService.update(documentConverter.convert(request), user);
    }
}
