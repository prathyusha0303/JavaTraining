package com.rgt.library;

import java.util.Scanner;

public class LibrarySystem {

	public static void main(String[] args) {
		Library library = new Library();
		Scanner scanner = new Scanner(System.in);
		Account[] accounts = new Account[10];
		Book book = new Book();
		Patron patron = new Patron();
		int choice;
		int accountCount = 0;

		while (true) {
			System.out.println("Welcome to the Library");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Logout");
			System.out.print("Choose an option: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				if (accountCount >= accounts.length) {
					System.out.println("Maximum number of Libraryaccounts reached.");
					break;
				}
				System.out.print("Enter your Librryaccount number: ");
				int accountNumber = scanner.nextInt();
				System.out.print("Enter PIN: ");
				int pin = scanner.nextInt();
				accounts[accountCount] = new Account(accountNumber, pin);
				System.out.println("LibraryAccount created successfully!");
				accountCount++;
				break;

			case 2:
				System.out.print("Enter LibraryAccount number: ");
				int accountNumberInput = scanner.nextInt();
				System.out.print("Enter PIN: ");
				int pinNumberInput = scanner.nextInt();
				Account currentAccount = null;
				for (int i = 0; i < accountCount; i++) {
					Account account = accounts[i];
					if (account.getAccountNumber() == accountNumberInput && account.getPin() == pinNumberInput) {
						currentAccount = account;
						break;
					}
				}
				if (currentAccount != null) {
					System.out.println("Login successful!");
					do {
						System.out.println("Welcome to the Library");
						System.out.println("1. Add Book");
						System.out.println("2. Add patron");
						System.out.println("3. Borrow Book");
						System.out.println("4. Return Book");
						System.out.println("5. Exit");
						System.out.println("Choose an opition :");
						choice = scanner.nextInt();

						switch (choice) {
						case 1:
							System.out.print("Enter book Title: ");
							String booktitle = scanner.next();
							book.setTitle(booktitle);
							System.out.print("Enter book author: ");
							String bookauthor = scanner.next();
							book.setAuthor(bookauthor);
							library.addbooks(book);
							break;
						case 2:
							System.out.print("Enter patron name: ");
							String patronname = scanner.next();
							patron.setPatronName(patronname);
							library.addpatrons(patron);
							break;
						case 3:
							System.out.print("Enter patron name: ");
							String patrons = scanner.next();
							patron.setPatronName(patrons);
							System.out.print("Enter book Title: ");
							String bookname = scanner.next();
							library.borrowbooks(patrons,bookname);
							break;
						case 4:
							System.out.print("Enter patron name: ");
							String namepatron = scanner.next();
							patron.setPatronName(namepatron);
							System.out.print("Enter book Title: ");
							String namebook = scanner.next();
							book.setTitle(namebook);
							library.returnbooks(namepatron, namebook);
							break;
						case 5:
							System.out.println("Exit successfully!");
						}
					} while (choice != 5);
				} else {
					System.out.println("Invalid account number or pin..");
				}
				break;
			case 3:
				System.out.println("Logout Sucessfully");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}

		}
	}
}		
