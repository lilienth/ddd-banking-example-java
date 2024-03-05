package de.wps.ddd.banking.sharedKernel;

import static de.wps.common.contracts.BaseContracts.require;

import java.util.Objects;

/**
 * ValueObject, representing a syntactically valid account number
 */
public class AccountNumber {

	public static AccountNumber of(int accountNumberValue) {
		require(accountNumberValue > 0, "accountNumberValue > 0");
		return new AccountNumber(accountNumberValue);
	}

	private final int accountNumberValue;

	private AccountNumber(int accountNumberValue) {
		this.accountNumberValue = accountNumberValue;
	}

	public int valueInt() {
		return this.accountNumberValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccountNumber that = (AccountNumber) o;
		return accountNumberValue == that.accountNumberValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumberValue);
	}
}
