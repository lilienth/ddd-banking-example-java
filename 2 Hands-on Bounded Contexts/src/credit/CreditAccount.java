package credit;

public class CreditAccount {
	private float balance;
	private int accountNumber;
	private CreditCustomer accountOwner;
	private Credit credit;

	public CreditAccount(int accountNumber, Credit credit) {
		super();
		this.balance = -credit.getAmountOfCredit();
		this.accountNumber = accountNumber;
		this.credit = credit;
		this.accountOwner = credit.getCustomer();
	}

	public float getBalance() {
		return balance;
	}

	public int getAccountnumber() {
		return accountNumber;
	}

	public void setBalance(float amount) {
		this.balance = amount;
	}

	public CreditCustomer getAccountowner() {
		return accountOwner;
	}

	public Credit getCredit() {
		return credit;
	}

}
