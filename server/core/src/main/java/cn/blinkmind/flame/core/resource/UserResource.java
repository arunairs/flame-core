package cn.blinkmind.flame.core.resource;

import cn.blinkmind.flame.core.bean.ObjectId;
import cn.blinkmind.flame.core.dto.UserDTO;
import cn.blinkmind.flame.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users")
public class UserResource extends AbstractResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ObjectId> create(@RequestBody final UserDTO userDTO) {
        Long id = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(id));
    }
}
