package de.wps.ddd.banking.credit;

import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CreditNumber;

public class Credit {
	private Amount amountOfCredit;
	private CreditNumber creditNumber;
	private Status status;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	};

	public Credit(Amount amountOfCredit) {
		super();
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = CreditNumber.getValidCreditNumber();
		this.status = Status.applied;
	}

	public Amount getAmountOfCredit() {
		return amountOfCredit;
	}

	public CreditNumber getCreditNumber() {
		return creditNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void grant(CreditAccount account) {
		this.status = Status.granted;
		this.account = account;
	}

	public CreditAccount getAccount() {
		return account;
	}

}
