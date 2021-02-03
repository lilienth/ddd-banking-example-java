package accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import credit.CreditService;

public class AccountManagementService {
	private Map<Integer, Customer> customerList = new HashMap<Integer, Customer>();
	private int customerNumberCounter = 0;
	private Map<Integer, Account> accountList = new HashMap<Integer, Account>();
	private int accountNumberCounter = 0;
	private CreditService creditService = null;

	public AccountManagementService(CreditService creditService) {

		this.creditService = creditService;
	}

	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(firstName, familyName, dateOfBirth, customerNumberCounter++);
		customerList.put(customer.getCustomerNumber(), customer);
		creditService.newCustomer(customer.getFirstName(), customer.getFamilyName(), customer.getDateOfBirth(),
				customer.getCustomerNumber());
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

//		accountList.get(debitorAccountNumber).withdraw(amount);
//		accountList.get(creditorAccountNumber).deposit(amount);

	}

	public Set<Integer> getAccountNumberList() {

		return accountList.keySet();
	}

	public Account getAccount(int accountNumber) {
		return accountList.get(accountNumber);
	}

	public Customer getCustomer(int accountNumber) {
		Customer customer = null;
		for (Map.Entry<Integer, Customer> entry : customerList.entrySet()) {
			List<Account> accountList = entry.getValue().getAccountList();
			Iterator<Account> iterator = accountList.iterator();
			while (iterator.hasNext() && customer == null) {
				Account account = iterator.next();
				if (account.getAccountnumber() == accountNumber) {
					customer = entry.getValue();
				}
			}
		}
		return customer;
	}
}
