package accounting;

import valueObjects.AccountNumber;
import valueObjects.Amount;

public class Account {
	private Amount balance;
	private AccountNumber accountNumber;
	private Customer accountOwner;

	public Account(Customer accountOwner) {
		super();
		this.balance = Amount.of(0);
		this.accountNumber = new AccountNumber();
		this.accountOwner = accountOwner;
	}

	public Amount getBalance() {
		return balance;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

	public void setBalance(Amount amount) {
		this.balance = amount;
	}

	public Customer getAccountowner() {
		return accountOwner;
	}

}
