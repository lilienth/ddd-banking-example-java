package de.wps.ddd.banking.sharedKernel;

import java.util.Objects;

public class CustomerNumber {
	private static int customerNumberCounter = 0;
	private final int customerNumber;

	private CustomerNumber() {
		this.customerNumber = customerNumberCounter++;
	}

	public static CustomerNumber getValidCustomerNumber() {
		return new CustomerNumber();
	}

	public static boolean isValidAccountNumber(int number) {
		return (number >= 0);
	}

	public int getCustomerNumber() {
		return this.customerNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CustomerNumber that = (CustomerNumber) o;
		return customerNumber == that.customerNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber);
	}
}
