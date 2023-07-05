package com.OnlineBiddingSystem.demo.entity;

public class Bid {
	
	    private Laptop item;
	    private Mobile mobile;
	    private Double bidAmount;
	    private boolean winningBid;

	    public Bid(Laptop item, Double bidAmount, boolean winningBid) {
	        this.item = item;
	        this.bidAmount = bidAmount;
	        this.winningBid = winningBid;
	    }

	    public Bid(Laptop item, Double bidAmount) {
	    	this.item = item;
	        this.bidAmount = bidAmount;
		}

		public Bid(Mobile mobile, double bidAmount) {
			this.mobile = mobile;
	        this.bidAmount = bidAmount;
		}

		public Laptop getItem() {
	        return item;
	    }

	    public Double getBidAmount() {
	        return bidAmount;
	    }

	    public boolean isWinningBid() {
	        return winningBid;
	    }

		public Mobile getMobile() {
			return mobile;
		}

		public void setMobile(Mobile mobile) {
			this.mobile = mobile;
		}

		public void setItem(Laptop item) {
			this.item = item;
		}

		public void setBidAmount(Double bidAmount) {
			this.bidAmount = bidAmount;
		}

		public void setWinningBid(boolean winningBid) {
			this.winningBid = winningBid;
		}
	    
	}
