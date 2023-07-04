package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public interface BiddingStrategy {
	
	public Double bid(Item item,User user,Double bidAmount);
}
