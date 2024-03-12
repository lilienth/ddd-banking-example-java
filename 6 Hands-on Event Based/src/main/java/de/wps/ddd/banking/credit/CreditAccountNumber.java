package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.require;

import de.wps.ddd.banking.accounting.CustomerNumber;
import java.util.Objects;

/**
 * ValueObject, representing a syntactically valid account number
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
public class CreditAccountNumber {

	public static boolean isValid(int accountNumberValue) {
		return accountNumberValue > 0;
	}

	public static CreditAccountNumber of(int accountNumberValue) {
		require(isValid(accountNumberValue), "isValid(accountNumberValue)");
		return new CreditAccountNumber(accountNumberValue);
	}

	private final int accountNumberValue;

	private CreditAccountNumber(int accountNumberValue) {
		this.accountNumberValue = accountNumberValue;
	}

	public int valueInt() {
		return this.accountNumberValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreditAccountNumber that = (CreditAccountNumber) o;
		return accountNumberValue == that.accountNumberValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumberValue);
	}
}
