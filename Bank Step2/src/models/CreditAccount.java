package models;

public class CreditAccount extends Account {

	public CreditAccount(float amountOfCredit, int accountNumber) {
		super(accountNumber);
		this.withdraw(amountOfCredit);

	}
	

}
