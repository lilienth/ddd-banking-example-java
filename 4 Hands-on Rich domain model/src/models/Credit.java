package models;

public class Credit {
	private float amountOfCredit;
	private Customer customer;
	private int creditNumber;
	private Status status;
	private CreditAccount account;


	public enum Status {
		applied,
		refused,
		granted,
		delayed,
		payed
	};
	
	public Credit(float amountOfCredit, Customer customer, int creditNumber) {
		super();
		this.amountOfCredit = amountOfCredit;
		this.customer = customer;
		this.customer.getCreditList().add(this);
		this.creditNumber = creditNumber;
		this.status = Status.applied;
	}

	public float getAmountOfCredit() {
		return amountOfCredit;
	}

	public void setAmountOfCredit(float amountOfCredit) {
		this.amountOfCredit = amountOfCredit;
	}

	public Customer getCustomer() {
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
