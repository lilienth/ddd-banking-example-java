package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import models.Account;
import models.Credit;
import models.CreditAccount;
import models.Customer;

public class AccountManagementService {
	private Map<Integer, Customer> customerList = new HashMap<Integer, Customer>();
	private int customerNumberCounter = 0;
	private Map<Integer, Account> accountList = new HashMap<Integer, Account>();
	private int accountNumberCounter = 0;
	
	
	public AccountManagementService() {
		
		Customer customer = this.newCustomer("Carola", "Lilienthal",LocalDate.of(1967, 9, 11));
		this.newAccount(1000, customer);
		this.newAccount(5000, customer);
		this.newAccount(2000,customer);
		

		customer = this.newCustomer("Hans", "Lilienthal",LocalDate.of(1968, 9, 11));
		this.newAccount(2000,customer);
		this.newAccount(5000,customer);
		
		customer = this.newCustomer("Dieter", "Lilienthal",LocalDate.of(1969, 9, 11));
		this.newAccount(3000,customer);
		this.newAccount(5000,customer);
		
		customer = this.newCustomer("Franz", "Lilienthal",LocalDate.of(1964, 9, 11));
		this.newAccount(4000,customer);
		this.newAccount(5000,customer);
		
		customer = this.newCustomer("Carsten", "Lilienthal",LocalDate.of(1965, 9, 11));
		this.newAccount(5000,customer);
	}
	
	public Customer newCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		Customer customer = new Customer(firstName, familyName, dateOfBirth, customerNumberCounter++);	
		customerList.put(customer.getCustomerNumber(), customer); 
		return customer;
	}
	
	public Account newAccount(float balance, Customer customer) {		
		Account account = new Account(accountNumberCounter++);	
		account.deposit(balance);
		accountList.put(account.getAccountnumber(), account);
		customer.getAccountList().add(account);
		return account;
	}
	
	public CreditAccount newCreditAccount(Credit credit) {		
		CreditAccount account = new CreditAccount(credit.getAmountOfCredit(), accountNumberCounter++);		
		accountList.put(account.getAccountnumber(), account);
		Customer customer = this.getCustomerForCredit(credit);
		customer.getAccountList().add(account);
		return account;
	}
	
	public Customer getCustomerForCredit(Credit credit) {
		Customer customer = null;
		for (Map.Entry<Integer, Customer> entry : customerList.entrySet()) {
			if (entry.getValue().getCreditList().contains(credit))
			{
				customer = entry.getValue();
			}			
		}
		return customer;
	}


	public List<Account> getAccountList() {
		return new ArrayList<Account>(accountList.values());
	}

	public List<Customer> getCustomerList() {
		return new ArrayList<Customer>(customerList.values());
	}

	
	public void transferMoney (float amount, int debitorAccountNumber, int creditorAccountNumber) {
		accountList.get(debitorAccountNumber).withdraw(amount);
		accountList.get(creditorAccountNumber).deposit(amount);
		
	}

	public Set<Integer> getAccountNumberList() {
		
		return accountList.keySet();
	}

	public Account getAccount(int accountNumber) {
		return accountList.get(accountNumber);
	}

}
