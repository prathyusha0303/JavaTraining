package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;

public class ItemFactory {

	public static Item createItem(String name,String description,Double startingBid) {
		return new Item(name, description, startingBid);
	}
}
