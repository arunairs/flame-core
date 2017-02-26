package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.service.SnapshotService;
import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.core.dto.PullDTO;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PullResource extends AbstractResource
{
    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "documents/{id}/pulls")
    public ResponseEntity<Void> create(@PathVariable(name = "id") long documentId, @RequestBody PullDTO pullDTO, @RequestAttribute(name = Attributes.USER) User user)
    {
        //snapshotService.pull(documentId, pull.getSnapshot(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
