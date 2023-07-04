package com.OnlineBiddingSystem.demo;

import com.OnlineBiddingSystem.demo.entity.Item;

public interface Observer {
	    void update(Item item, Object arg);
	}

