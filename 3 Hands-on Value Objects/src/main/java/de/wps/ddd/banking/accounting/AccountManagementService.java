package de.wps.ddd.banking.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.wps.ddd.banking.credit.CreditService;
import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;

public class AccountManagementService {
	private Map<CustomerNumber, Customer> customerList = new HashMap<CustomerNumber, Customer>();
	private Map<AccountNumber, Account> accountList = new HashMap<AccountNumber, Account>();
	private CreditService creditService = null;

	public AccountManagementService(CreditService creditService) {

		this.creditService = creditService;
	}

	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(firstName, familyName, dateOfBirth);
		customerList.put(customer.getCustomerNumber(), customer);
		creditService.newCustomer(customer.getFirstName(), customer.getFamilyName(), customer.getDateOfBirth(),
				customer.getCustomerNumber());
		return customer;
	}

	public Account newAccount(Amount balance, Customer customer) {
		Account account = new Account(customer);
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

	public void transferMoney(Amount amount, AccountNumber debitorAccountNumber, AccountNumber creditorAccountNumber) {
		Amount balance = accountList.get(debitorAccountNumber).getBalance();
		balance = balance.subtract(amount);
		accountList.get(debitorAccountNumber).setBalance(balance);

		balance = accountList.get(creditorAccountNumber).getBalance();
		balance = balance.add(amount);
		accountList.get(creditorAccountNumber).setBalance(balance);
	}

	public Set<AccountNumber> getAccountNumberList() {

		return accountList.keySet();
	}

	public Account getAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Customer getCustomer(AccountNumber accountNumber) {
		Customer customer = accountList.get(accountNumber).getAccountowner();
		return customer;
	}

}
