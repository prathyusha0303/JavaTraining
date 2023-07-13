
package com.user.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.demo.entity.UserEntity;

@Service
public interface UserService {

	UserEntity createAccount(UserEntity userEntity) throws IOException;

	List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException;

	UserEntity getUser(Long userId) throws IOException, ClassNotFoundException;

	boolean deleteUser(Long userId);

	UserEntity updateUser(UserEntity userEntity, Long userId) throws ClassNotFoundException, IOException;

	
}
