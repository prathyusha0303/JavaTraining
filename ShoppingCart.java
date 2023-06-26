package com.rgt.onlineshoping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

	public HashMap<Product, Integer> items;

	public ShoppingCart() {
		items = new HashMap<>();
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void addItem(Product product, Integer quantity) {
		items.put(product, items.getOrDefault(product, 0) + quantity);
		/*
		 * if (items.containsKey(product)) { int currentQuantity = items.get(product);
		 * items.put(product, currentQuantity + quantity); } else { items.put(product,
		 * quantity); }
		 */
	}

	public void removeItem(Product product) {
		items.remove(product);
	}

	public Double getTotalPrice() {
		double totalPrice = 0.0;
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	public void displayCart() {
		if (items.isEmpty()) {
			System.out.println("Your shopping cart is empty.");
		} else {
			for (Map.Entry<Product, Integer> entry : items.entrySet()) {
				Product product = entry.getKey();
				int quantity = entry.getValue();
				System.out.println("Product: " + product.getName());
				System.out.println("Quantity: " + quantity);
				System.out.println("--------------------");
			}
			System.out.println("Total Price: $" + getTotalPrice());
		}
	}
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sb.append(product.getName())
                    .append("\t")
                    .append(product.getPrice())
                    .append("\t")
                    .append(quantity)
                    .append("\t")
                    .append(product.getPrice() * quantity)
                    .append("\n");
        }
        return sb.toString();
    }
}
