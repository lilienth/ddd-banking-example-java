package de.wps.ddd.banking.credit;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;

public class CreditAccount {
	private Amount balance;
	private AccountNumber accountNumber;
	private CreditCustomer accountOwner;
	private Credit credit;

	public CreditAccount(Credit credit) {
		super();
		this.credit = credit;
		this.balance = Amount.of(0).subtract(this.credit.getAmountOfCredit());
		this.accountNumber = AccountNumber.getValidAccountNumber();
		this.accountOwner = credit.getCustomer();
	}

	public Amount getBalance() {
		return balance;
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

	public void deposit(Amount amount) {
		this.balance = balance.add(amount);
	}

	public void withdraw(Amount amount) {
		this.balance = balance.subtract(amount);
	}

	public CreditCustomer getAccountowner() {
		return accountOwner;
	}

}
