package de.wps.ddd.banking.sharedKernel;

import static de.wps.common.contracts.BaseContracts.require;

import java.util.Objects;

public class CreditNumber {

	public static CreditNumber of(int creditNumberValue) {
		require(creditNumberValue > 0, "creditNumberValue > 0");
		return new CreditNumber(creditNumberValue);
	}

	private final int creditNumberValue;

	private CreditNumber(int creditNumberValue) {
		this.creditNumberValue = creditNumberValue;
	}

	public int valueInt() {
		return this.creditNumberValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreditNumber that = (CreditNumber) o;
		return creditNumberValue == that.creditNumberValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditNumberValue);
	}
}
