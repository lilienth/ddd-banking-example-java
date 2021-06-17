package de.wps.ddd.banking.models;

public class Account {
	private float balance;
	private int accountNumber;
	private Customer accountOwner;

	public Account(int accountNumber, Customer accountOwner) {
		super();
		this.balance = 0;
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getAccountnumber() {
		return accountNumber;
	}

	public Customer getAccountowner() {
		return accountOwner;
	}

}
