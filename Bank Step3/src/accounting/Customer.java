package accounting;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Customer {
	private String firstName;
	private String familyName;
	private LocalDate dateOfBirth;
	private int customerNumber;
	private List<Account> accountList;
	
	public Customer(String firstName, String familyName, LocalDate dateOfBirth, int customerNumber) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = customerNumber;
		accountList = new ArrayList<Account>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}
		
	public List<Account> getAccountList() {
		return accountList;
	}

}
