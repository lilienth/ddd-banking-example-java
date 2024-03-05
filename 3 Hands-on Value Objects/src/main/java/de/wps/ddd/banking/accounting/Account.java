package de.wps.ddd.banking.accounting;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;

public class Account {
	private final AccountNumber accountNumber;
	private final Customer accountOwner;
	private Amount balance;

	public Account(Customer accountOwner, AccountNumber accountNumber) {
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
		this.balance = Amount.of(0);
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount amount) {
		this.balance = amount;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

	public Customer getAccountowner() {
		return accountOwner;
	}

}
