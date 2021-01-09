package accounting;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import valueObjects.CustomerNumber;


public class Customer {
	private String firstName;
	private String familyName;
	private LocalDate dateOfBirth;
	private CustomerNumber customerNumber;
	private List<Account> accountList;
	
	public Customer(String firstName, String familyName, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = new CustomerNumber();
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

	public CustomerNumber getCustomerNumber() {
		return customerNumber;
	}
		
	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}



}
