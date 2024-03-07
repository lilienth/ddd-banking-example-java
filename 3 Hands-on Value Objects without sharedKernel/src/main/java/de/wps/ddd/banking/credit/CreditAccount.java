package de.wps.ddd.banking.credit;

public class CreditAccount {
	private Amount balance;
	private AccountNumber accountNumber;
	private CreditCustomer accountOwner;
	private Credit credit;

	public CreditAccount(Credit credit, AccountNumber accountNumber) {
		super();
		this.credit = credit;
		this.balance = Amount.of(0).subtract(this.credit.getAmountOfCredit());
		this.accountNumber = accountNumber;
		this.accountOwner = credit.getCustomer();
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

	public CreditCustomer getAccountowner() {
		return accountOwner;
	}

}
