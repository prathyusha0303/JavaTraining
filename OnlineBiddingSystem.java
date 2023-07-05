package com.OnlineBiddingSystem.demo;

import java.util.List;
import java.util.Scanner;

import com.OnlineBiddingSystem.demo.entity.Bid;
import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.Laptop;
import com.OnlineBiddingSystem.demo.entity.User;
import com.OnlineBiddingSystem.demo.factory.ItemFactory;
import com.OnlineBiddingSystem.demo.strategy.AutomaticBiddingStrategy;
import com.OnlineBiddingSystem.demo.strategy.BiddingStrategy;
import com.OnlineBiddingSystem.demo.strategy.IncrementalBiddingStrategy;

/**
 * Hello world!
 *
 */
public class OnlineBiddingSystem {
	private static UserManagementService userManagementService;
	private static ItemManagementService itemManagementService;
	private static User currentUser;

	public OnlineBiddingSystem() {
		this.userManagementService = new UserManagementService();
		this.itemManagementService = new ItemManagementService();
		this.currentUser = null;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		showMainMenu(scanner);
	}

	public void showMainMenu(Scanner scanner) {
		while (true) {
			System.out.println("Welcome to the Online Bidding System");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			Integer option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createAccount(scanner);
				break;
			case 2:
				login(scanner);
				break;
			case 3:
				System.out.println("Thanks for using Bidding System. GoodBye!!");
				return;
			default:
				System.out.println("Invalid option. Please try again..");
			}
		}
	}

	private static void login(Scanner scanner) {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String pwd = scanner.nextLine();

		currentUser = userManagementService.authenticateUser(username, pwd);
		if (currentUser != null) {
			System.out.println("Login Sucessful. Welcome, " + currentUser.getUserName());
			showUserMenu(scanner);
		} else {
			System.out.println("Invalid username or password. Please try again..");
		}
	}

	private static void createAccount(Scanner scanner) {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String pwd = scanner.nextLine();
		userManagementService.createUser(username, pwd);
		System.out.println("Account created successfully..");

	}

	private static void showUserMenu(Scanner scanner) {

		while (true) {
			System.out.println("Welcome to online Bidding system.");
			System.out.println("1. Add Items");
			System.out.println("2. Search Items");
			System.out.println("3. View Bidding History");
			System.out.println("4. Logout");
			System.out.print("Choose an option: ");
			Integer option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addItems(scanner);
				break;
			case 2:
				searchItems(scanner);
				break;
			case 3:
				viewBiddingHistory(scanner);
				break;
			case 4:
				currentUser = null;
				System.out.println("You have logged out. Bye.");
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void addItems(Scanner scanner) {

		System.out.println("1. Laptops");
		System.out.println("2. Mobile");

		System.out.print("Choose the item: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			System.out.println("Enter item name");
			String name = scanner.nextLine();
			System.out.print("Enter item description: ");
			String description = scanner.nextLine();
			System.out.print("Enter starting bid amount: ");
			double startingBid = scanner.nextDouble();
			scanner.nextLine(); // Consume newline character
			Item item = ItemFactory.createItem(name, description, startingBid);
			itemManagementService.addItem(item);
			System.out.println("Item added successfully.");
			break;

		case 2:
			System.out.println("Enter item name");
			name = scanner.nextLine();
			System.out.print("Enter item description: ");
			description = scanner.nextLine();
			System.out.print("Enter starting bid amount: ");
			startingBid = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter its model");
			String model = scanner.nextLine();
			item = ItemFactory.createItem(name, description, startingBid, model);
			itemManagementService.addItem(item);
			System.out.println("Item added successfully.");
			break;
		}
	}

	private static void searchItems(Scanner scanner) {
		System.out.print("Enter search keyword: ");
		String keyword = scanner.nextLine();
		List<Item> searchResults = itemManagementService.searchItems(keyword);
		System.out.println("Serach results: ");
		int count = 1;
		for (Item item : searchResults) {
			System.out.println(count + "." + item.getName() + "-" + item.getDescription() + "-Current Highest bid : Rs "
					+ item.getCurrentHighestBid());
			count++;
		}
		System.out.print("Enter an item index to place a bid, or '0' to go back: ");
		Integer selectedIndex = Integer.parseInt(scanner.next());
		if (selectedIndex >= 1 && selectedIndex <= searchResults.size()) {
			Item selectedItem = searchResults.get(selectedIndex - 1);
			System.out.print("Enter a bid amount: Rs ");
			double bidAmount = scanner.nextDouble();
			boolean enter = false;
			while (!enter) {
				if (selectedItem.getCurrentHighestBid() > bidAmount) {
					System.out.println("Amount Should be Greater than currentBid");
				} else {
					enter = true;
				}
			}
			System.out.println("1. Incremental Bidding");
			System.out.println("2. Automatic Bidding");
			System.out.print("Choose a bidding strategy: ");
			Integer choice = scanner.nextInt();
			BiddingStrategy biddingStrategy;
			switch (choice) {
			case 1:
				biddingStrategy = new IncrementalBiddingStrategy();
				break;
			case 2:
				biddingStrategy = new AutomaticBiddingStrategy();
				break;
			default:
				System.out.println("Invalid option. Placing bid using Incremental Bidding strategy.");
				return;
			}
			User currentUser = userManagementService.getCurrentUser();
			double newBidding = biddingStrategy.bid(selectedItem, currentUser, bidAmount);
			System.out.println("Bid placed succesfully. your bid amount :" + newBidding);

		}

	}

	private static void viewBiddingHistory(Scanner scanner) {
		User currentUser = userManagementService.getCurrentUser();
		if (currentUser != null) {
			List<Bid> biddingHistory = currentUser.getBiddingHistory();
			if (biddingHistory.isEmpty()) {
				System.out.println("Your bidding history is empty");
			} else {
				System.out.println("Your bidding history:");
				for (Bid bid : biddingHistory) {
					Laptop item = bid.getItem();
					System.out.println(item.getName() + " - " + item.getDescription() + " - Bid amount: Rs "
							+ bid.getBidAmount() + " - Bidding Person: " + currentUser.getUserName());
				}
			}
		} else {
			System.out.println("You are not logged in. Please log in.");
		}
	}

	public static void main(String[] args) {
		OnlineBiddingSystem onlineBiddingSystem = new OnlineBiddingSystem();
		onlineBiddingSystem.run();
	}
}
