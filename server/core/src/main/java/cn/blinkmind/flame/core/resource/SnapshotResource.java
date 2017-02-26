package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.repository.model.Snapshot;
import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.bean.ObjectId;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.repository.model.Archive;
import cn.blinkmind.flame.repository.model.User;
import cn.blinkmind.flame.core.service.SnapshotService;
import cn.blinkmind.flame.core.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        Assert.isNotNull(snapshot, Errors.RESOURCE_NOT_FOUND);
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
