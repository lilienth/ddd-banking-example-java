package credit;

public class CustomerNumber {
	private final int customerNumber;

	private CustomerNumber(int number) {
		customerNumber = number;
	}

	public static CustomerNumber getValidCustomerNumber(int number) {
		return new CustomerNumber(number);
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
