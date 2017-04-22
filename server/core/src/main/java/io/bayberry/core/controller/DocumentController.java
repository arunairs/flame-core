package io.bayberry.core.controller;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.authentication.User;
import io.bayberry.core.dto.DocumentRequest;
import io.bayberry.core.dto.DocumentResponse;
import io.bayberry.core.dto.converter.DocumentConverter;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.DocumentService;
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
        return new DocumentResponse(documentService.get(id, user).orElseThrow(() -> Errors.RESOURCE_NOT_FOUND));
    }
}
