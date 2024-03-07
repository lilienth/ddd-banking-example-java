package de.wps.ddd.banking.credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.wps.ddd.banking.sharedKernel.CustomerNumber;

public class CreditCustomer {
	private String firstName;
	private String familyName;
	private LocalDate dateOfBirth;
	private CustomerNumber customerNumber;
	private List<CreditAccount> accountList;
	private List<Credit> creditList;

	public CreditCustomer(CustomerNumber customerNumber, String firstName, String familyName, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = customerNumber;
		accountList = new ArrayList<>();
		creditList = new ArrayList<>();
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

	public List<CreditAccount> getAccountList() {
		return accountList;
	}

	public List<Credit> getCreditList() {
		return creditList;
	}

}
