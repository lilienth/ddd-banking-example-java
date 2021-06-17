package de.wps.ddd.banking.sharedKernel;

public class Amount {
	private final float amount;

	private Amount(float amount) {
		this.amount = amount;
	}

	public static Amount of(float amount) {
		return new Amount(amount);
	}

	public Amount add(Amount secondAmount) {
		return new Amount(this.amount + secondAmount.amount);
	}

	public Amount subtract(Amount secondAmount) {
		return new Amount(this.amount - secondAmount.amount);
	}

	public static boolean isValidAmount(float amount) {
		return (amount >= 0);
	}

	public float value() {
		return this.amount;
	}

	@Override
	public boolean equals(Object obj) {
		Amount secondAmount = (Amount) obj;
		return this.amount == secondAmount.amount;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
