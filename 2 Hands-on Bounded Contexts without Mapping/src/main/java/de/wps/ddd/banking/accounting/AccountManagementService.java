package de.wps.ddd.banking.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountManagementService {
	private Map<Integer, Customer> customerList = new HashMap<Integer, Customer>();
	private int customerNumberCounter = 0;
	private Map<Integer, Account> accountList = new HashMap<Integer, Account>();
	private int accountNumberCounter = 0;

	public AccountManagementService() {

	}

	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(firstName, familyName, dateOfBirth, customerNumberCounter++);
		customerList.put(customer.getCustomerNumber(), customer);
		return customer;
	}

	public Account newAccount(float balance, Customer customer) {
		Account account = new Account(accountNumberCounter++, customer);
		account.setBalance(balance);
		accountList.put(account.getAccountnumber(), account);
		customer.getAccountList().add(account);
		return account;
	}

	public List<Account> getAccountList() {
		return new ArrayList<Account>(accountList.values());
	}

	public List<Customer> getCustomerList() {
		return new ArrayList<Customer>(customerList.values());
	}

	public void transferMoney(float amount, int debitorAccountNumber, int creditorAccountNumber) {
		float balance = accountList.get(debitorAccountNumber).getBalance();
		balance = balance - amount;
		accountList.get(debitorAccountNumber).setBalance(balance);

		balance = accountList.get(creditorAccountNumber).getBalance();
		balance = balance + amount;
		accountList.get(creditorAccountNumber).setBalance(balance);
	}

	public Set<Integer> getAccountNumberList() {

		return accountList.keySet();
	}

	public Account getAccount(int accountNumber) {
		return accountList.get(accountNumber);
	}

	public Customer getCustomer(int accountNumber) {
		Customer customer = accountList.get(accountNumber).getAccountowner();
		return customer;
	}
}
