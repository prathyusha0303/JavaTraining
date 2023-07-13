package com.user.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity userEntity) throws IOException {
		return userService.createAccount(userEntity);
	}
	
	@GetMapping("{id}")
	@Secured("ROLE_ADMIN") // Requires ROLE_ADMIN to access this endpoint
    public UserEntity getUser(@PathVariable("id") Long id) throws IOException, ClassNotFoundException {
        return userService.getUser(id);
    }

	@Secured("ROLE_ADMIN")
	@GetMapping
	public List<UserEntity> getUsers() throws ClassNotFoundException, IOException{
		return userService.getAllUsers();
	}
	
	@PutMapping("/{id}")
	@Secured({"ROLE_ADMIN", "ROLE_USER"}) // Requires ROLE_ADMIN or ROLE_USER to access this endpoint
	public UserEntity updateuser(@PathVariable("id") Long id,@RequestBody UserEntity userEntity) throws ClassNotFoundException, IOException {
		return userService.updateUser(userEntity, id);
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN") // Requires ROLE_ADMIN to access this endpoint
	public void deleteById(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
