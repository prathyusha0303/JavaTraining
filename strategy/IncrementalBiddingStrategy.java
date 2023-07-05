package com.OnlineBiddingSystem.demo.strategy;

import com.OnlineBiddingSystem.demo.UserManagementService;
import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class IncrementalBiddingStrategy implements BiddingStrategy {
	UserManagementService managementService = new UserManagementService();

	public Double bid(Item item, User user, Double bidAmount) {
		double newBid = bidAmount;
		item.placeBid(user, newBid);
		managementService.notifyObserver(item);
		return newBid;
	}

}
