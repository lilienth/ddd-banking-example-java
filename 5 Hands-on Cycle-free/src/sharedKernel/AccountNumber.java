package valueObjects;

public class AccountNumber {
	private static int accountNumberCounter = 0;
	private final int accountNumber;

	private AccountNumber() {
		this.accountNumber = accountNumberCounter++;
	}

	public static AccountNumber getValidAccountNumber() {
		return new AccountNumber();
	}

	public static boolean isValidAccountNumber(int number) {
		return (number >= 0);
	}

	public int value() {
		return this.accountNumber;
	}

	@Override
	public boolean equals(Object obj) {
		AccountNumber secondNumber = (AccountNumber) obj;
		return this.accountNumber == secondNumber.accountNumber;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
