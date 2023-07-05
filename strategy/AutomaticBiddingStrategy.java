package com.OnlineBiddingSystem.demo.strategy;

import com.OnlineBiddingSystem.demo.UserManagementService;
import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class AutomaticBiddingStrategy implements BiddingStrategy {
	UserManagementService managementService = new UserManagementService();

	public Double bid(Item item, User user, Double bidAmount) {
		Double currentBid = item.getCurrentHighestBid();
		Double newBid = currentBid + 1.0;
		item.placeBid(user, newBid);
		managementService.notifyObserver(item);
		return newBid;
	}
}
