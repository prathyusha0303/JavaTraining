package com.rgt.onlineshoping;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author PrathyushaBashaveni
 *
 */
public class OnlineShoppingSystemClass {
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ProductCatalog productCatalog = new ProductCatalog();
		ShoppingCart shoppingCart = new ShoppingCart();
		OrderHistory orderHistory = new OrderHistory();

		System.out.println("Welcome to the Online Shopping System!");

		while (true) {
			System.out.println("\nPlease select an option:");
			System.out.println("1. Load Product Catalog");
			System.out.println("2. Save Product Catalog");
			System.out.println("3. Add Product");
			System.out.println("4. Remove Product");
			System.out.println("5. Display Product Catalog");
			System.out.println("6. Add Product to Cart");
			System.out.println("7. Remove Product from Cart");
			System.out.println("8. Display Cart");
			System.out.println("9. Place Order");
			System.out.println("10. Save order file");
			System.out.println("11. Display Order History");
			System.out.println("0. Exit");

			Integer option = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (option) {
			case 1:
				//Load Product Catalog
				System.out.print("Enter the file name to load the product catalog: ");
				String loadFileName = scanner.nextLine();
				productCatalog.loadProducts(loadFileName);
				break;
			case 2:
				//Save Product Catalog
				System.out.print("Enter the file name to save the product catalog: ");
				String saveFileName = scanner.nextLine();
				productCatalog.saveProducts(saveFileName);
				break;
			case 3:
				//Add Product
				System.out.print("Enter the product name: ");
				String name = scanner.nextLine();
				System.out.print("Enter the product description: ");
				String description = scanner.nextLine();
				System.out.print("Enter the product price: ");
				double price = scanner.nextDouble();
				System.out.print("Enter the product quantity: ");
				Integer quantity = scanner.nextInt();
				scanner.nextLine(); // Consume newline
				try {
					Product product = new Product(name, description, price, quantity);
					productCatalog.addProduct(product);
					System.out.println("Product added successfully!");
				} catch (Exception e) {
					System.out.println("Product is not added.");
				}
				break;
			case 4:
				//Remove Product
				System.out.print("Enter the name of the product to remove: ");
				String productName = scanner.nextLine();
				productCatalog.removeProduct(productName);
				System.out.println("Product removed successfully!");
				break;
			case 5:
				//Display Product Catalog
				productCatalog.displayProducts();
				break;
			case 6:
				// Add Product to Cart
				System.out.print("Enter the name of the product to add to the cart: ");
				String addToCartName = scanner.nextLine();
				Product addToCartProduct = productCatalog.getProduct(addToCartName);
				if (addToCartProduct != null) {
					System.out.print("Enter the quantity to add: ");
					Integer addToCartQuantity = scanner.nextInt();
					scanner.nextLine(); 
					shoppingCart.addItem(addToCartProduct, addToCartQuantity);
					System.out.println("Product added to the cart successfully!");
				} else {
					System.out.println("Product not found in the catalog.");
				}
				break;
			case 7://Remove Product from Cart
				System.out.print("Enter the name of the product to remove from the cart: ");
				String removeFromCartName = scanner.nextLine();
				Product removeFromCartProduct = productCatalog.getProduct(removeFromCartName);
				if (removeFromCartProduct != null) {
					shoppingCart.removeItem(removeFromCartProduct);
					System.out.println("Product removed from the cart successfully!");
				} else {
					System.out.println("Product not found in the cart.");
				}
				break;
			case 8://Showing cart 
				shoppingCart.displayCart();
				break;
			case 9:
				placeOrder(productCatalog, shoppingCart, orderHistory);
				break;
			case 10: // Save order history
				System.out.print("Enter the file name to save the order history to: ");
				String orderFileName = scanner.nextLine();
				orderHistory.saveOrderHistory(orderFileName);
				break;
			case 11:
			//Display Orders
			 displayOrderHistrory(orderHistory);
			 break;
			case 0:
				System.out.println("Thank you for using the Online Shopping System. Goodbye!");
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	/**
	 * placeOrder method implementation
	 * @param productCatalog
	 * @param shoppingCart
	 * @param orderHistory
	 */
	private static void placeOrder(ProductCatalog productCatalog, ShoppingCart shoppingCart,
			OrderHistory orderHistory) {
		HashMap<Product, Integer> items = shoppingCart.getItems();
		if (items.isEmpty()) {
			System.out.println("The cart is empty. Please add products to the cart before placing an order.");
			return;
		}
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			if (product.getQuantity() < quantity) {
				System.out.println("Insufficient quantity available for product: " + product.getName());
				return;
			}
		}
		Order order = new Order(items);
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			product.setQuantity(product.getQuantity() - quantity);
		}
		orderHistory.addOrder(order);
		shoppingCart.getItems().clear();

		System.out.println("Order placed successfully!");
		System.out.println("Confirmation Number: " + order.getConfirmationNumber());
		System.out.println("Total Price: $" + order.getTotalPrice());
	}
	/**
	 * displayOrderHistrory method implementation
	 * @param orderHistory
	 */
	private static void displayOrderHistrory(OrderHistory orderHistory) {
		orderHistory.displayOrderHistory();
	}


}
