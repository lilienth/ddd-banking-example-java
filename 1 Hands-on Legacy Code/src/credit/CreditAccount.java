package credit;

import models.Account;

public class CreditAccount {
	private Credit credit;

	public CreditAccount(Account account, Credit credit) {
		account.setBalance(-(credit.getAmountOfCredit()));
		this.credit = credit;
	}

	public Credit getCredit() {
		return credit;
	}

}
