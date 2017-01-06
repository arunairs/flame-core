package cn.blinkmind.flame.server.resource;

import cn.blinkmind.flame.server.bean.ObjectId;
import cn.blinkmind.flame.server.service.UserService;
import cn.blinkmind.flame.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
public class UserResource extends AbstractResource
{
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ObjectId> create(@RequestBody User user)
    {
        user = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(user.getId()));
    }
}
