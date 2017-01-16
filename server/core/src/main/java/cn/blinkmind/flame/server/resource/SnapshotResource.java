package cn.blinkmind.flame.server.resource;

import cn.blinkmind.flame.server.annotation.Token;
import cn.blinkmind.flame.server.bean.ObjectId;
import cn.blinkmind.flame.server.constant.Attributes;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.service.SnapshotService;
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
public class SnapshotResource extends AbstractResource
{
    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "branches/{id}/snapshots")
    public ResponseEntity<ObjectId> create(@PathVariable(name = "id") long branchId, @RequestBody Snapshot snapshot, @RequestAttribute(name = Attributes.USER) User user)
    {
        snapshot = snapshotService.create(branchId, snapshot, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(snapshot.getId()));
    }

    @Token
    @DeleteMapping(path = "snapshots/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id, @RequestAttribute(name = Attributes.USER) User user)
    {
        snapshotService.delete(id, user);
        return ResponseEntity.noContent().build();
    }

    @Token
    @GetMapping(path = "snapshots/{id}")
    public ResponseEntity<Snapshot> get(@PathVariable(name = "id") long id, @RequestAttribute(name = Attributes.USER) User user)
    {
        Snapshot snapshot = snapshotService.require(id, user);
        return ResponseEntity.ok(snapshot);
    }

    @Token
    @PatchMapping(path = "snapshots/{id}")
    public ResponseEntity<Void> patch(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> map, @RequestAttribute(name = Attributes.USER) User user)
    {
        snapshotService.patch(id, map, user);
        return ResponseEntity.ok().build();
    }

    @Token
    @PutMapping(path = "snapshots/{id}/archive")
    public ResponseEntity<Void> updateArchive(@PathVariable(name = "id") long id, @RequestBody Archive archive, @RequestAttribute(name = Attributes.USER) User user)
    {
        snapshotService.updateArchive(id, archive, user);
        return ResponseEntity.ok().build();
    }
}
