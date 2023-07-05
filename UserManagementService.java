package com.OnlineBiddingSystem.demo;

import java.util.ArrayList;
import java.util.List;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;
import com.OnlineBiddingSystem.demo.observer.OberverService;
import com.OnlineBiddingSystem.demo.observer.Observer;

public class UserManagementService implements OberverService {
	private User currentUser;
	private static List<User> users =new ArrayList<User>();
	public static List<Observer> observers = new ArrayList<>();

	public UserManagementService() {

	}

	public void createUser(String username, String password) {
		User user = new User(username, password);
		users.add(user);
		observers.add(user);
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

	public List<User> getALl() {
		return users;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserver(Item item) {
		for (Observer observer : observers) {
			observer.update(item,observer);
		}
	}
}
