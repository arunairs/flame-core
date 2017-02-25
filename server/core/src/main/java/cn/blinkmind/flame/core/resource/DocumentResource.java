package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.bean.ObjectId;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.core.dto.DocumentDTO;
import cn.blinkmind.flame.core.dto.UserDTO;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ObjectId> create(@RequestBody DocumentDTO documentDTO, @RequestAttribute(name = Attributes.USER) UserDTO userDTO) {
        Long id = documentService.create(documentDTO, userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(id));
    }

    @Token
    @GetMapping(path = "{documentId}")
    public ResponseEntity<DocumentDTO> get(@PathVariable(name = "documentId") long documentId, @RequestAttribute(name = Attributes.USER) UserDTO userDTO) {
        DocumentDTO documentDTO = documentService.get(documentId, userDTO);
        Assert.isNotNull(documentDTO, Errors.RESOURCE_NOT_FOUND);
        return ResponseEntity.ok(documentDTO);
    }
}
