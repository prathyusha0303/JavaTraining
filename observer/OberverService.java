package com.OnlineBiddingSystem.demo.observer;

import com.OnlineBiddingSystem.demo.entity.Item;

public interface OberverService {
	public void addObserver(Observer observer);

	void notifyObserver(Item item);
}
