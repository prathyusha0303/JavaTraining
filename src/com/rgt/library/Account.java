
package com.rgt.library;

public class Account {
	private int accountNumber;
	private int pin;

	public Account(int accountNumber, int pin) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
	}
	public int getAccountNumber() {
		return accountNumber;
	}

	public int getPin() {
		return pin;
	}
}
