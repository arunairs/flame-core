package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.repository.entity.Push;
import io.bayberry.repository.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushResource {

    @Token
    @PostMapping(path = "documents/{documentId}/pushes")
    public ResponseEntity<Void> create(@PathVariable(name = "documentId") Long documentId, @RequestBody Push input, @RequestAttribute(name = Attributes.USER) User user) {
        //snapshotService.push(documentId, push.getSnapshot(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
