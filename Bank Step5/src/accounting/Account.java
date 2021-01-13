package accounting;

import valueObjects.AccountNumber;
import valueObjects.Amount;

public class Account {
	private Amount balance;
	private AccountNumber accountNumber;

	public Account() {
		super();
		this.balance = new Amount(0);
		this.accountNumber = new AccountNumber();
	}

	public Amount getBalance() {
		return balance;
	}

	public void withdraw(Amount amount) {
		this.balance = this.balance.subtract(amount);
	}
	
	public void deposit(Amount amount) {
		this.balance = this.balance.add(amount);
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}
	
}
