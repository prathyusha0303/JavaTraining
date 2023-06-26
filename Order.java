package com.rgt.onlineshoping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
	private static Integer ordercount = 1;

	private Integer confirmationNumber;
	private HashMap<Product, Integer> Items;
	private Double totalPrice;

	public Order(HashMap<Product, Integer> items) {
		this.confirmationNumber = ordercount++;
		Items = items;
		this.totalPrice = calculateTotalPrice();
	}

	private Double calculateTotalPrice() {
		Double totalPrice = 0.0;
		for (Map.Entry<Product, Integer> entry : Items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	public Integer getConfirmationNumber() {
		return confirmationNumber;
	}

	public HashMap<Product, Integer> getItems() {
		return Items;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Order [confirmationNumber=" + confirmationNumber + ", Items=" + Items + ", totalPrice=" + totalPrice
				+ "]";
	}

	public void displayOrder() {
		System.out.println("Confirmation Number: " + confirmationNumber);
		System.out.println("Total Price: $" + totalPrice);
		for (Map.Entry<Product, Integer> entry : Items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			System.out.println("Product: " + product.getName());
			System.out.println("Quantity: " + quantity);
			System.out.println("--------------------");
		}
	}

}
