package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.bean.ObjectId;
import io.bayberry.core.constant.Attributes;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.SnapshotService;
import io.bayberry.repository.model.Archive;
import io.bayberry.repository.model.Snapshot;
import io.bayberry.repository.model.User;
import io.bayberry.core.common.validation.Matcher;
import io.bayberry.core.common.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static io.bayberry.core.common.validation.Validator.validateThat;

@RestController
public class SnapshotResource extends AbstractResource {
    private SnapshotService snapshotService;

    @Autowired
    public SnapshotResource(SnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @Token
    @PostMapping(path = "branches/{id}/snapshots")
    public ResponseEntity<ObjectId> create(@PathVariable(name = "id") long branchId, @RequestBody Snapshot snapshotData, @RequestAttribute(name = Attributes.USER) User user) {
        Snapshot snapshot = snapshotService.create(branchId, snapshotData, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(snapshot.getId()));
    }

    @Token
    @DeleteMapping(path = "snapshots/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id, @RequestAttribute(name = Attributes.USER) User user) {
        snapshotService.delete(id, user);
        return ResponseEntity.noContent().build();
    }

    @Token
    @GetMapping(path = "snapshots/{id}")
    public ResponseEntity<Snapshot> get(@PathVariable(name = "id") long id, @RequestAttribute(name = Attributes.USER) User user) {
        Snapshot snapshot = snapshotService.get(id, user);
        Validator.validateThat(snapshot, Matcher.not(Matcher.nil()), () -> {
            throw Errors.RESOURCE_NOT_FOUND;
        });
        return ResponseEntity.ok(snapshot);
    }

    @Token
    @PatchMapping(path = "snapshots/{id}")
    public ResponseEntity<Void> patch(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> map, @RequestAttribute(name = Attributes.USER) User user) {
        snapshotService.patch(id, map, user);
        return ResponseEntity.ok().build();
    }

    @Token
    @PutMapping(path = "snapshots/{id}/archive")
    public ResponseEntity<Void> updateArchive(@PathVariable(name = "id") long id, @RequestBody Archive archive, @RequestAttribute(name = Attributes.USER) User user) {
        snapshotService.updateArchive(id, archive, user);
        return ResponseEntity.ok().build();
    }
}
