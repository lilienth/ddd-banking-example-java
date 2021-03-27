package accounting;

import sharedKernel.AccountNumber;
import sharedKernel.Amount;

public class Account {
	private Amount balance;
	private AccountNumber accountNumber;
	private Customer accountOwner;

	public Account(Customer accountOwner) {
		super();
		this.balance = Amount.of(0);
		this.accountNumber = AccountNumber.getValidAccountNumber();
		this.accountOwner = accountOwner;
	}

	public Amount getBalance() {
		return balance;
	}

	public void withdraw(Amount amount) {
		assert amount != null;
		assert amount.isLessOrEquals(this.getBalance());
		this.balance = this.balance.subtract(amount);
	}

	public void deposit(Amount amount) {
		assert amount != null;
		this.balance = this.balance.add(amount);
	}

	public AccountNumber getAccountnumber() {
		return accountNumber;
	}

	public Customer getAccountowner() {
		return accountOwner;
	}

}
