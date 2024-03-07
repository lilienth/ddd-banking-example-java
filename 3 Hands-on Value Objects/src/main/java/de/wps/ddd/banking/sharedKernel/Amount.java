package de.wps.ddd.banking.sharedKernel;

import java.util.Objects;

/**
 * ValueObject, representing a syntactically valid amount
 *
 * <p>Implemented as a class with:</p>
 * <ul>
 *     <li>isValid method to check for validity</li>
 *     <li>private constructor and  a factory method "of" to control object creation and decouple external and internal representation</li>
 *     <li>equals/hashCode based on the internal int value</li>
 * </ul>
 *
 * @see CustomerNumber for an alternative way of implementing value objects
 * @see CreditNumber for an alternative way of implementing value objects
 */
public class Amount {

	public static boolean isValidAmount(float amount) {
		// All float values are considered valid
		return true;
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
