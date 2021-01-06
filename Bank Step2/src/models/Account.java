package models;

public class Account {
	private float balance;
	private int accountNumber;

	public Account(int accountNumber) {
		super();
		this.balance = 0;
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void withdraw(float amount) {
		this.balance = this.balance - amount;
	}
	
	public void deposit(float amount) {
		this.balance = this.balance + amount;
	}

	public int getAccountnumber() {
		return accountNumber;
	}
	
}
