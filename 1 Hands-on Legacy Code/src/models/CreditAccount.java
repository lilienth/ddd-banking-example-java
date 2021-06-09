package models;

import credit.Credit;

public class CreditAccount extends Account {
	private Credit credit;

	public CreditAccount(int accountNumber, Credit credit) {
		super(accountNumber, credit.getCustomer());
		this.setBalance(-(credit.getAmountOfCredit()));
		this.credit = credit;

	}

	public Credit getCredit() {
		return credit;
	}

}
