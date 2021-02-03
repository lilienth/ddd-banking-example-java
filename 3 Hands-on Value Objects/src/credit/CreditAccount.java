package credit;

import valueObjects.AccountNumber;
import valueObjects.Amount;

public class CreditAccount {
	private Amount balance;
	private AccountNumber accountNumber;

	public CreditAccount(Amount amountOfCredit) {
		super();
		this.balance = new Amount(0);
		this.accountNumber = new AccountNumber();
		this.withdraw(amountOfCredit);

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
