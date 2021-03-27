package valueObjects;

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
	public boolean equals(Object obj) {
		CustomerNumber secondNumber = (CustomerNumber) obj;
		return this.customerNumber == secondNumber.customerNumber;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
