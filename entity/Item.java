package com.OnlineBiddingSystem.demo.entity;

public interface Item {
	
	public void placeBid(User user, double bidAmount);
	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description) ;

	public Double getCurrentHighestBid() ;

	public void setCurrentHighestBid(Double currentHighestBid);
	public User getHighestBidder() ;

	public void setHighestBidder(User highestBidder) ;
}
