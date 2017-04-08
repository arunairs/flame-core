package io.bayberry.core.resource;

import io.bayberry.core.domain.Users;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "users")
public class UserResource {
    private Users users;

    @Autowired
    public UserResource(Users users) {
        this.users = users;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User output = users.create(user);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(output.getId()).toUri())
                .body(output);
    }
}
