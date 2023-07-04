package com.OnlineBiddingSystem.demo.entity;

public class Item {

	private String name;
	private String description;
	private Double currentHighestBid;
	private User highestBidder;

	public Item(String name, String description, Double startingBid) {
		super();
		this.name = name;
		this.description = description;
		this.currentHighestBid = startingBid;
	}

	public void placeBid(User user, double bidAmount) {
		if (bidAmount > currentHighestBid) {
			currentHighestBid = bidAmount;
			highestBidder = user;
			user.addBid(new Bid(this, bidAmount));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCurrentHighestBid() {
		return currentHighestBid;
	}

	public void setCurrentHighestBid(Double currentHighestBid) {
		this.currentHighestBid = currentHighestBid;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		this.highestBidder = highestBidder;
	}
}
