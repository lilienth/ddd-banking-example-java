package de.wps.ddd.banking.accounting;

import static de.wps.common.contracts.BaseContracts.ensureNotNull;
import static de.wps.common.contracts.BaseContracts.require;
import static de.wps.common.contracts.BaseContracts.requireNotNull;
import static de.wps.common.contracts.StringContracts.requireHasText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Customer {
	private final String firstName;
	private final String familyName;
	private final LocalDate dateOfBirth;
	private final CustomerNumber customerNumber;
	private final List<Account> accountList;

	public Customer(CustomerNumber customerNumber, String firstName, String familyName, LocalDate dateOfBirth) {
		requireNotNull(customerNumber, "customerNumber");
		requireHasText(firstName, "firstName");
		requireHasText(familyName, "familyName");
		requireNotNull(dateOfBirth, "dateOfBirth");

		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = customerNumber;
		accountList = new ArrayList<>();
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
		requireNotNull(accountNumber, "accountNumber");
		require(hasAccount(accountNumber), "hasAccount(accountNumber)");

		Account account = accountList.stream()
				.filter(a -> a.getAccountnumber().equals(accountNumber))
				.findAny().orElse(null);

		ensureNotNull(account, "account");
		return account;
	}

	public void addAccount(Account account) {
		accountList.add(account);
	}

	public boolean hasAccount(AccountNumber accountNumber) {
		requireNotNull(accountNumber, "accountNumber");
		return accountList.stream().anyMatch(a -> a.getAccountnumber().equals(accountNumber));
	}
}
