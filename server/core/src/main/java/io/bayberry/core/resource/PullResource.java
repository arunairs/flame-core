package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.constant.Attributes;
import io.bayberry.repository.model.Pull;
import io.bayberry.repository.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PullResource extends AbstractResource {

    @Token
    @PostMapping(path = "documents/{documentId}/pulls")
    public ResponseEntity<Void> create(@PathVariable(name = "documentId") long documentId, @RequestBody Pull input, @RequestAttribute(name = Attributes.USER) User user) {
        //snapshotService.pull(documentId, pull.getSnapshot(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
