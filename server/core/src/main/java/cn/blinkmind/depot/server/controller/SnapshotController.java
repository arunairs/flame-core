package cn.blinkmind.depot.server.controller;

import cn.blinkmind.depot.server.annotation.Token;
import cn.blinkmind.depot.server.bean.web.ObjectId;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Snapshot;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.service.BranchService;
import cn.blinkmind.depot.server.service.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/11/2016 4:09 PM
 */
@RestController
public class SnapshotController {
    @Autowired
    private BranchService branchService;

    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "workspace/snapshots")
    public ResponseEntity<ObjectId> create(@RequestBody Snapshot snapshot, @RequestAttribute(name = "user") User user) {
        snapshot = snapshotService.create(snapshot, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(snapshot.getId()));
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
        return ResponseEntity.ok().build();
    }
}
