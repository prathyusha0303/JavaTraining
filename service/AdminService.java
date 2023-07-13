package com.user.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.demo.entity.UserEntity;
import com.user.demo.repository.UserRepository;
@Service
public class AdminService {
	private final UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserEntity> getAllUsers() throws ClassNotFoundException, IOException {
		return userRepository.findAll();
	}

	public UserEntity getUser(long id) throws ClassNotFoundException, IOException {
		return userRepository.findById(id);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}
