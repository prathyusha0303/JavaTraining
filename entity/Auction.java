package com.OnlineBiddingSystem.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class Auction {

	private static Auction instance = null;
	private List<Item> items;

	private Auction() {
		items = new ArrayList<Item>();
	}

	public static Auction getInstance() {
		if(instance==null) {
			instance=new Auction();
		}
		return instance;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
		
	}
	

}
