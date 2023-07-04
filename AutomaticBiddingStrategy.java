package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class AutomaticBiddingStrategy implements BiddingStrategy{

    public Double bid(Item item, User user,Double bidAmount) {
		Double currentBid=item.getCurrentHighestBid();
		Double newBid=currentBid+1.0;
		item.placeBid(user,newBid);
		return newBid;
	}
}
