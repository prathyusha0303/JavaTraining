package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class IncrementalBiddingStrategy implements BiddingStrategy{

    public Double bid(Item item, User user,Double bidAmount) {
        double currentBid = item.getCurrentHighestBid();
        double newBid = currentBid + bidAmount;
        item.placeBid(user, newBid);
        return newBid;
    }

}
