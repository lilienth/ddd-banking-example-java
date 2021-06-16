package de.wps.ddd.banking.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;

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
		this.customerNumber = CustomerNumber.getValidCustomerNumber();
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

	public Account getAccount(AccountNumber accountNumber) {
		Account account = null;
		Iterator<Account> iterator = accountList.iterator();
		boolean found = false;
		while (iterator.hasNext() && !found) {
			account = iterator.next();
			if (account.getAccountnumber() == accountNumber) {
				found = true;
			}
		}
		return account;
	}

	public void addAccount(Account account) {
		accountList.add(account);
	}

	public boolean hasAccount(AccountNumber accountNumber) {
		boolean returnValue = false;
		Iterator<Account> iterator = accountList.iterator();
		while (iterator.hasNext() && returnValue == false) {
			Account account = iterator.next();
			if (account.getAccountnumber() == accountNumber) {
				returnValue = true;
			}
		}
		return returnValue;
	}

}
