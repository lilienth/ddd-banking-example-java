package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String firstName;
	private String familyName;
	private LocalDate dateOfBirth;
	private int customerNumber;
	private List<Account> accountList;
	private List<Credit> creditList;
	
	public Customer(String firstName, String familyName, LocalDate dateOfBirth, int customerNumber) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = customerNumber;
		accountList = new ArrayList<Account>();
		creditList = new ArrayList<Credit>();
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

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Credit> getCreditList() {
		return creditList;
	}

	public void setCreditList(List<Credit> creditList) {
		this.creditList = creditList;
	}

}
