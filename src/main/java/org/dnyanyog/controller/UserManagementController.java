package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {
	@Autowired
	UserManagementService service;

	@PostMapping("/auth/user/add")
	public AddUserResponse addUser(@RequestBody AddUserRequest request) throws Exception {
		return service.addUser(request);
	}

	@GetMapping("/auth/user/search/{userId}")
	public AddUserResponse searchUser(@PathVariable int userId) {
		return service.searchUser(userId);
	}

	@GetMapping("/auth/user/search")
	public List<Users> getuser() {
		return service.getUser();
	}

}
