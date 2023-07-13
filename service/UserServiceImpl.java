package com.user.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.entity.UserEntity;
import com.user.demo.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity createAccount(UserEntity userEntity) throws IOException {
		userEntity.setId(System.currentTimeMillis());
		return userRepository.save(userEntity);
	}

	@Override
	public List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUser(Long userId) throws IOException, ClassNotFoundException {
		return userRepository.findById(userId);
	}

	@Override
	public boolean deleteUser(Long id) {
		return userRepository.deleteById(id);
	}
	@Override
	public UserEntity updateUser(UserEntity userEntity, Long userId) throws ClassNotFoundException, IOException {
		return userRepository.updateUser(userEntity, userId);
	}

}
