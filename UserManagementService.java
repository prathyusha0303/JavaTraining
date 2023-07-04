package com.OnlineBiddingSystem.demo;

import java.util.ArrayList;
import java.util.List;

import com.OnlineBiddingSystem.demo.entity.User;

public class UserManagementService {
	private User currentUser;
	private List<User> users;

	public UserManagementService() {
		this.users = new ArrayList<User>();

	}
	public void createUser(String username, String password) {
		User user = new User(username, password);
		users.add(user);
	}
	public User authenticateUser(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				return user;
			} else {
				System.out.println("Authentication failure..");
			}
		}
		return null;
	}
	public User getCurrentUser() {
		return currentUser;
	}
}
