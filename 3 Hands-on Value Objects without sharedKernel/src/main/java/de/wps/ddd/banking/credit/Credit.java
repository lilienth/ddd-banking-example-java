package de.wps.ddd.banking.credit;

public class Credit {
	private Amount amountOfCredit;
	private CreditNumber creditNumber;
	private Status status;
	private CreditCustomer customer;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	};

	public Credit(CreditNumber creditNumber, CreditCustomer customer, Amount amountOfCredit) {
		super();
		this.customer = customer;
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = creditNumber;
		this.status = Status.applied;
	}

	public Amount getAmountOfCredit() {
		return amountOfCredit;
	}

	public void setAmountOfCredit(Amount amountOfCredit) {
		this.amountOfCredit = amountOfCredit;
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
