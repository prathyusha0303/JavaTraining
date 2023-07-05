package com.OnlineBiddingSystem.demo.entity;

public class Mobile implements Item {
	private String name;
	private String description;
	private Double currentHighestBid;
	private String model;
	private User highestBidder;

	public Mobile(String name, String description, Double currentHighestBid,String model) {
		super();
		this.name = name;
		this.description = description;
		this.currentHighestBid = currentHighestBid;
		this.model=model;
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

	@Override
	public void placeBid(User user, double bidAmount) {
		if (bidAmount > currentHighestBid) {
			currentHighestBid = bidAmount;
			highestBidder = user;		
			user.addBid(new Bid(this, bidAmount));		
		}
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

	}
