package de.wps.ddd.banking.credit;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;

public class CreditAccount {
	private Amount balance;
	private final AccountNumber accountNumber;
	private final CreditCustomer accountOwner;

	public CreditAccount(Credit credit, AccountNumber accountNumber) {
		super();
		this.accountNumber = accountNumber;
		this.accountOwner = credit.getCustomer();
		this.balance = Amount.of(0).subtract(credit.getAmountOfCredit());
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

	public CreditCustomer getAccountOwner() {
		return accountOwner;
	}

}
