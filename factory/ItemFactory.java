package com.OnlineBiddingSystem.demo.factory;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.Laptop;
import com.OnlineBiddingSystem.demo.entity.Mobile;

public class ItemFactory {

	public static Item createItem(String name, String description, Double startingBid) {
		return new Laptop(name, description, startingBid);
	}

	public static Item createItem(String name, String description, Double startingBid, String model) {
		return new Mobile(name, description, startingBid, model);
	}
}
