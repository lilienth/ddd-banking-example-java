package models;

public class CreditAccount extends Account {
	private Credit credit;

	public CreditAccount(Credit credit, int accountNumber) {
		super(accountNumber, credit.getCustomer());
		this.setBalance(-(credit.getAmountOfCredit()));
		this.credit = credit;

	}

	public Credit getCredit() {
		return credit;
	}
	

}
