package com.OnlineBiddingSystem.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String userName;
	private String password;
	private List<Bid> biddingHistory;
	
	public void addBid(Bid bid) {
		biddingHistory.add(bid);
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.biddingHistory = new ArrayList<Bid>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Bid> getBiddingHistory() {
		return biddingHistory;
	}

	public void setBiddingHistory(List<Bid> biddingHistory) {
		this.biddingHistory = biddingHistory;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", biddingHistory=" + biddingHistory + "]";
	}

	}
	

