package valueObjects;

public class AccountNumber {
	private static int accountNumberCounter = 0;
    private final int accountNumber;
	
    public AccountNumber() {
	 this.accountNumber = accountNumberCounter++;
    }
	    
    public static boolean isValidAccountNumber(int number)
    {
    	return (number >= 0);
    }
    
    public int getAccountNumber() {
    	return this.accountNumber;
    }

	@Override
	public boolean equals(Object obj) {
		AccountNumber secondNumber = (AccountNumber)obj;
		return this.accountNumber == secondNumber.accountNumber;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
    
}
