package credit;

public class CreditAccount {
	private float balance;
	private int accountNumber;

	public CreditAccount(float amountOfCredit, int accountNumber) {
		super();
		this.balance = 0;
		this.accountNumber = accountNumber;
		this.withdraw(amountOfCredit);

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
