package models;

public class CreditAccount extends Account {
	private Credit credit;

	public CreditAccount(Credit credit, int accountNumber) {
		super(accountNumber);
		this.withdraw(credit.getAmountOfCredit());
		this.credit = credit;

	}

	public Credit getCredit() {
		return credit;
	}
	

}
