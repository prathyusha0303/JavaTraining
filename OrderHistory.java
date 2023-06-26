package com.rgt.onlineshoping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3982679587836350262L;
	public List<Order> orders;
	public OrderHistory(String orderFileName) {
		orders = new ArrayList<Order>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public OrderHistory() {
		orders = new ArrayList<>();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getAllOrders() {
		return orders;
	}

	public void saveOrderHistory(String fileName) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			objectOutputStream.writeObject(orders);
			displayOrderHistory();
			System.out.println("Order history saved to file: " + fileName);
		} catch (IOException e) {
			System.err.println("Error saving order history: " + e.getMessage());
		}
	}

	public void displayOrderHistory() {
		if (orders.isEmpty()) {
			System.out.println("Order history is empty.");
		} else {
			for (Order order : orders) {
				order.displayOrder();
				System.out.println("====================");
			}
		}

	}

}
