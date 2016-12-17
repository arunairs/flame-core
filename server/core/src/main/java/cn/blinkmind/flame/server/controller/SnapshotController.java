package cn.blinkmind.flame.server.controller;

import cn.blinkmind.flame.server.annotation.Token;
import cn.blinkmind.flame.server.bean.web.ObjectId;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.service.BranchService;
import cn.blinkmind.flame.server.service.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SnapshotController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "workspace/snapshots")
    public ResponseEntity<ObjectId> createSnapshot(@RequestBody Snapshot snapshot, @RequestAttribute(name = "user") User user) {
        snapshot = snapshotService.create(snapshot, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(snapshot.getId()));
    }

    @Token
    @DeleteMapping(path = "workspace/snapshots/{id}")
    public ResponseEntity<Void> deleteSnapshot(@PathVariable(name = "id") long id, @RequestAttribute(name = "user") User user) {
        snapshotService.delete(id, user);
        return ResponseEntity.noContent().build();
    }

    @Token
    @GetMapping(path = "workspace/snapshots/{id}")
    public ResponseEntity<Snapshot> getSnapshot(@PathVariable(name = "id") long id, @RequestAttribute(name = "user") User user) {
        Snapshot snapshot = snapshotService.require(id, user);
        return ResponseEntity.ok(snapshot);
    }

    @Token
    @PutMapping(path = "workspace/snapshots/{id}/archive")
    public ResponseEntity<Void> updateArchive(@PathVariable(name = "id") long id, @RequestBody Archive archive, @RequestAttribute(name = "user") User user) {
        snapshotService.updateArchive(id, archive, user);
        return ResponseEntity.ok().build();
    }
}
