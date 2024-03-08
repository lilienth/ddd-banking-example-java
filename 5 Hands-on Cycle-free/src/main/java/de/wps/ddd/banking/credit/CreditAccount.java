package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.requireNotNull;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;

public class CreditAccount {
	private final AccountNumber accountNumber;
	private Amount balance;


	public CreditAccount(AccountNumber accountNumber, Amount amountOfCredit) {
		requireNotNull(accountNumber, "accountNumber");
		requireNotNull(amountOfCredit, "amountOfCredit");

		this.accountNumber = accountNumber;
		this.balance = Amount.of(0).subtract(amountOfCredit);
	}

	public Amount getBalance() {
		return balance;
	}

	public AccountNumber getAccountNumber() {
		return accountNumber;
	}

	public void deposit(Amount amount) {
		requireNotNull(amount, "amount");
		this.balance = balance.add(amount);
	}

	public void withdraw(Amount amount) {
		requireNotNull(amount, "amount");
		this.balance = balance.subtract(amount);
	}
}
