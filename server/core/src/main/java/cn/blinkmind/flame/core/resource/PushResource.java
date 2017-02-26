package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.dto.PushDTO;
import cn.blinkmind.flame.core.service.SnapshotService;
import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushResource extends AbstractResource
{
    @Autowired
    private SnapshotService snapshotService;

    @Token
    @PostMapping(path = "documents/{id}/pushes")
    public ResponseEntity<Void> create(@PathVariable(name = "id") long documentId, @RequestBody PushDTO pushDTO, @RequestAttribute(name = Attributes.USER) User user)
    {
        //snapshotService.push(documentId, push.getSnapshot(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
