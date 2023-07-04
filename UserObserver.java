package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class UserObserver implements Observer{
		private User user;

	    public UserObserver(User user) {
	        this.user = user;
	    }

	    public void update(Item item, Object arg) {
	        if (arg instanceof Boolean) {
	            boolean isOutbid = (Boolean) arg;
	            if (isOutbid) {
	                System.out.println("You have been outbid on item: " + item.getName());
	                System.out.println("");
	            }
	        }
	    }
		
	}
