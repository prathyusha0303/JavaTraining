package com.OnlineBiddingSystem.demo.observer;

import com.OnlineBiddingSystem.demo.entity.Item;

public interface Observer {
	void update(Item item, Observer observer);
}
