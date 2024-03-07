package de.wps.ddd.banking.credit;

import java.util.Objects;

public class Amount {

	public static boolean isValidAmount(float amount) {
		return (amount >= 0);
	}

	public static Amount of(float amount) {
		return new Amount(amount);
	}

	private final float amount;

	private Amount(float amount) {
		this.amount = amount;
	}

	public Amount add(Amount secondAmount) {
		return of(this.amount + secondAmount.amount);
	}

	public Amount subtract(Amount secondAmount) {
		return of(this.amount - secondAmount.amount);
	}

	public float value() {
		return this.amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Amount amount1 = (Amount) o;
		return Float.compare(amount, amount1.amount) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
}
