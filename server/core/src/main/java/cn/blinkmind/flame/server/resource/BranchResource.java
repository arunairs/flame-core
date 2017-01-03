package cn.blinkmind.flame.server.resource;

import cn.blinkmind.flame.server.annotation.Token;
import cn.blinkmind.flame.server.bean.web.ObjectId;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BranchResource extends AbstractResource
{
    @Autowired
    private BranchService branchService;

    @Token
    @PostMapping(path = "documents/{documentId}/branches")
    public ResponseEntity<ObjectId> create(@PathVariable(name = "documentId") long documentId, @RequestBody Branch branch, @RequestAttribute(name = USER_KEY) User user)
    {
        branch = branchService.create(documentId, branch, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(branch.getId()));
    }

    @Token
    @PatchMapping(path = "branches/{id}")
    public ResponseEntity<ObjectId> patch(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> map, @RequestAttribute(name = USER_KEY) User user)
    {
        branchService.patch(id, map, user);
        return ResponseEntity.ok().build();
    }

    @Token
    @DeleteMapping(path = "branches/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id, @RequestAttribute(name = USER_KEY) User user)
    {
        branchService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}