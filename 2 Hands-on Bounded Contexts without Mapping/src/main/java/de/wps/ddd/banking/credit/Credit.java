package de.wps.ddd.banking.credit;

public class Credit {
	private float amountOfCredit;
	private int creditNumber;
	private Status status;
	private CreditCustomer customer;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	};

	public Credit(int creditNumber, CreditCustomer customer, float amountOfCredit) {
		super();
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = creditNumber;
		this.customer = customer;
		this.customer.getCreditList().add(this);
		this.status = Status.applied;
	}

	public float getAmountOfCredit() {
		return amountOfCredit;
	}

	public void setAmountOfCredit(float amountOfCredit) {
		this.amountOfCredit = amountOfCredit;
	}

	public CreditCustomer getCustomer() {
		return customer;
	}

	public int getCreditNumber() {
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
