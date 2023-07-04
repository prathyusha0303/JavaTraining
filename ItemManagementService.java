package com.OnlineBiddingSystem.demo;

import java.util.ArrayList;
import java.util.List;

import com.OnlineBiddingSystem.demo.entity.Auction;
import com.OnlineBiddingSystem.demo.entity.Item;

public class ItemManagementService {

	private Auction auction;

	public ItemManagementService() {
		this.auction=Auction.getInstance();
	}

	public void addItem(String name,String description,Double startingBid) {
		Item item=ItemFactory.createItem(name, description, startingBid);
		auction.addItem(item);
	}
	public List<Item> searchItems(String keyword){
		List<Item>searchResults=new ArrayList<Item>();
		for(Item item: auction.getItems()) {
			if(item.getName().toLowerCase().contains(keyword.toLowerCase())) {
				searchResults.add(item);
			}
		}
		return searchResults;
	}
	
}
