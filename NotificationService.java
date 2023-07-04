package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class NotificationService {

	public void notifyUser(User user,Item item) {
		System.out.println("You have been outbid on item: "+item.getName());
	}
}
