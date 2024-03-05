package de.wps.ddd.banking.credit;

import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CreditNumber;

public class Credit {
	private final CreditNumber creditNumber;
	private final CreditCustomer customer;
	private final Amount amountOfCredit;
	private Status status;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	}

	public Credit(CreditCustomer customer, CreditNumber creditNumber, Amount amountOfCredit) {
		super();
		this.customer = customer;
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = creditNumber;
		this.status = Status.applied;
	}

	public Amount getAmountOfCredit() {
		return amountOfCredit;
	}



	public CreditCustomer getCustomer() {
		return customer;
	}

	public CreditNumber getCreditNumber() {
		return creditNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CreditAccount getAccount() {
		return account;
	}

	public void setAccount(CreditAccount account) {
		this.account = account;
	}

}
