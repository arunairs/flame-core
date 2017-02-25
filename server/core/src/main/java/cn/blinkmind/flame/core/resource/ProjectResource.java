package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.annotation.Token;
import cn.blinkmind.flame.core.bean.ObjectId;
import cn.blinkmind.flame.core.constant.Attributes;
import cn.blinkmind.flame.repository.entity.Project;
import cn.blinkmind.flame.repository.entity.User;
import cn.blinkmind.flame.core.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectResource extends AbstractResource {
    
    @Autowired
    private ProjectService projectService;

    @Token
    @PostMapping(path = "projects")
    public ResponseEntity<ObjectId> create(@RequestBody Project project, @RequestAttribute(name = Attributes.USER) User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(1L));
    }
}
