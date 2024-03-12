package de.wps.ddd.banking.accounting;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AccountManagementService {
	private final Map<CustomerNumber, Customer> customerList = new HashMap<>();
	private final Map<AccountNumber, Account> accountList = new HashMap<>();
	private final CustomerRegistrationEventPublisher customerRegistrationEventPublisher;
	private final AccountNumberFactory accountNumberFactory;
	private final CustomerNumberFactory customerNumberFactory;

	public AccountManagementService(CustomerRegistrationEventPublisher customerRegistrationEventPublisher) {
		this(customerRegistrationEventPublisher, new AccountNumberFactory(), new CustomerNumberFactory());
	}

	AccountManagementService(CustomerRegistrationEventPublisher customerRegistrationEventPublisher, AccountNumberFactory accountNumberFactory, CustomerNumberFactory customerNumberFactory) {
		this.customerRegistrationEventPublisher = customerRegistrationEventPublisher;
		this.accountNumberFactory = accountNumberFactory;
		this.customerNumberFactory = customerNumberFactory;
	}

	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(customerNumberFactory.newCustomerNumber(), firstName, familyName, dateOfBirth);
		customerList.put(customer.getCustomerNumber(), customer);

		customerRegistrationEventPublisher.newCustomerRegistered(customer);
		return customer;
	}

	public Account newAccount(Amount balance, Customer customer) {
		Account account = new Account(accountNumberFactory.newAccountNumber());
		account.deposit(balance);
		accountList.put(account.getAccountnumber(), account);
		customer.addAccount(account);
		return account;
	}

	public List<Account> getAccountList() {
		return List.copyOf(accountList.values());
	}

	public List<Customer> getCustomerList() {
		return List.copyOf(customerList.values());
	}

	public void transferMoney(Amount amount, AccountNumber debitorAccountNumber, AccountNumber creditorAccountNumber) {
		accountList.get(debitorAccountNumber).withdraw(amount);
		accountList.get(creditorAccountNumber).deposit(amount);
	}

	public Set<AccountNumber> getAccountNumberList() {
		return Set.copyOf(accountList.keySet());
	}

	public Account getAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Customer getCustomer(AccountNumber accountNumber) {
		Optional<Customer> customer = customerList.values().stream()
				.filter(c -> c.hasAccount(accountNumber))
				.findAny();
		return customer.orElse(null);
	}

}
