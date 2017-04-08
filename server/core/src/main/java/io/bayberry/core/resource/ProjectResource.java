package io.bayberry.core.resource;

import io.bayberry.core.annotation.Token;
import io.bayberry.core.bean.ObjectId;
import io.bayberry.core.constant.Attributes;
import io.bayberry.repository.entity.Project;
import io.bayberry.repository.entity.User;
import io.bayberry.core.domain.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectResource {

    @Autowired
    private Projects projects;

    @Token
    @PostMapping(path = "projects")
    public ResponseEntity<ObjectId> create(@RequestBody Project project, @RequestAttribute(name = Attributes.USER) User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(1L));
    }
}
