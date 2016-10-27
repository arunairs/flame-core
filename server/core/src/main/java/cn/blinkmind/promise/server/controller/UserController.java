package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 12/10/2016 3:11 PM
 */
@RestController
@RequestMapping(path = "users")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ObjectId> create(@RequestBody User userData)
	{
		User user = userService.create(userData);
		return ResponseEntity.ok(new ObjectId(user.getId()));
	}
}
