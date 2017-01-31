package cn.blinkmind.flame.server.resource;

import cn.blinkmind.flame.server.annotation.Token;
import cn.blinkmind.flame.server.constant.Attributes;
import cn.blinkmind.flame.server.repository.entity.Pull;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.service.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PullResource extends AbstractResource
{
    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "documents/{id}/pulls")
    public ResponseEntity<Void> create(@PathVariable(name = "id") long documentId, @RequestBody Pull pull, @RequestAttribute(name = Attributes.USER) User user)
    {
        snapshotService.pull(documentId, pull.getSnapshot(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
