package com.user.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.entity.UserEntity;
import com.user.demo.service.AdminService;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {
	@Autowired
    private AdminService adminService ;


    public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable long id) throws ClassNotFoundException, IOException {
    	UserEntity userEntity = adminService.getUser(id);
        if (userEntity != null) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        adminService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() throws ClassNotFoundException, IOException {
        List<UserEntity> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
