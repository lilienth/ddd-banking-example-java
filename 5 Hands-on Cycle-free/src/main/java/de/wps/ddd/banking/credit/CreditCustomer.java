package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.ensureNotNull;
import static de.wps.common.contracts.BaseContracts.require;
import static de.wps.common.contracts.BaseContracts.requireNotNull;
import static de.wps.common.contracts.StringContracts.requireHasText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;

public class CreditCustomer {
	private final CustomerNumber customerNumber;
	private final String firstName;
	private final String familyName;
	private final LocalDate dateOfBirth;
	private final List<CreditAccount> accountList;
	private final List<Credit> creditList;

	public CreditCustomer(CustomerNumber customerNumber, String firstName, String familyName, LocalDate dateOfBirth) {
		requireNotNull(customerNumber, "customerNumber");
		requireHasText(firstName, "firstName");
		requireHasText(familyName, "familyName");
		requireNotNull(dateOfBirth, "dateOfBirth");

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

	public CreditAccount getAccount(AccountNumber accountNumber) {
		requireNotNull(accountNumber, "accountNumber");
		require(hasAccount(accountNumber), "hasAccount(accountNumber)");

		CreditAccount account = accountList.stream()
				.filter(a -> a.getAccountNumber().equals(accountNumber))
				.findAny().orElse(null);

		ensureNotNull(account, "account");
		return account;
	}

	public void addAccount(CreditAccount account) {
		requireNotNull(account, "account");
		accountList.add(account);
	}

	public boolean hasAccount(AccountNumber accountNumber) {
		requireNotNull(accountNumber, "accountNumber");
		return accountList.stream().anyMatch(a -> a.getAccountNumber().equals(accountNumber));
	}

	public List<Credit> getCreditList() {
		return List.copyOf(creditList);
	}

	public void addCredit(Credit credit) {
		requireNotNull(credit, "credit");
		creditList.add(credit);
	}
}
