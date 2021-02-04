package credit;

import valueObjects.Amount;
import valueObjects.CreditNumber;

public class Credit {
	private Amount amountOfCredit;
	private CreditNumber creditNumber;
	private Status status;
	private CreditCustomer customer;
	private CreditAccount account;

	public enum Status {
		applied, refused, granted, delayed, payed
	};

	public Credit(CreditCustomer customer, Amount amountOfCredit) {
		super();
		this.customer = customer;
		this.amountOfCredit = amountOfCredit;
		this.creditNumber = CreditNumber.getValidCreditNumber();
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

	public void grant(CreditAccount account) {
		assert account != null;
		assert canBeGranted();

		this.status = Status.granted;
		this.account = account;
	}

	public boolean canBeGranted() {
		return (this.status != Status.refused && this.status != Status.granted);
	}

	public CreditAccount getAccount() {
		return account;
	}

}
