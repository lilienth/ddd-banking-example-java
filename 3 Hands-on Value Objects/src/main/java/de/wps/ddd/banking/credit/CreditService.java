package de.wps.ddd.banking.credit;

import de.wps.ddd.banking.sharedKernel.AccountNumberFactory;
import de.wps.ddd.banking.sharedKernel.CreditNumberFactory;
import de.wps.ddd.banking.sharedKernel.CustomerNumberFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.wps.ddd.banking.credit.Credit.Status;
import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CreditNumber;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;

public class CreditService {
	private final Map<CustomerNumber, CreditCustomer> customerList = new HashMap<>();
	private final Map<AccountNumber, CreditAccount> accountList = new HashMap<>();
	private final Map<CreditNumber, Credit> creditList = new HashMap<>();
	private final AccountNumberFactory accountNumberFactory;
	private final CreditNumberFactory creditNumberFactory;

	public CreditService() {
		this(new AccountNumberFactory(), new CreditNumberFactory());
	}
	CreditService(AccountNumberFactory accountNumberFactory, CreditNumberFactory creditNumberFactory) {
		this.accountNumberFactory = accountNumberFactory;
		this.creditNumberFactory = creditNumberFactory;
    }


	// should only be called by AccountManagementService
	public void newCustomer(String firstName, String familyName, LocalDate dateOfBirth, CustomerNumber customerNumber) {
		customerList.put(customerNumber, new CreditCustomer(customerNumber, firstName, familyName, dateOfBirth));

	}

	public CreditAccount newCreditAccount(Amount balance, Credit credit) {
		CreditAccount account = new CreditAccount(credit, accountNumberFactory.newAccountNumber());
		account.setBalance(balance);
		accountList.put(account.getAccountnumber(), account);
		CreditCustomer customer = credit.getCustomer();
		customer.getAccountList().add(account);
		return account;
	}

	public CreditNumber applyForCredit(Amount amount, CreditCustomer customer) {
		Credit credit = new Credit(customer, creditNumberFactory.newCreditNumber(), amount);
		customer.getCreditList().add(credit);
		CreditNumber creditNumber = credit.getCreditNumber();
		creditList.put(creditNumber, credit);

		return creditNumber;
	}

	public CreditAccount grantCredit(CreditNumber creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = this.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit(CreditNumber creditNumber) {
		return creditList.get(creditNumber);
	}

	public Credit getCreditFromAccountNumber(AccountNumber accountNumber) {
		Credit credit = null;
		for (Map.Entry<CreditNumber, Credit> entry : creditList.entrySet()) {
			if (entry.getValue().getAccount().getAccountnumber().equals(accountNumber)) {
				credit = entry.getValue();
			}
		}
		return credit;
	}

	public void makePaymentForCredit(CreditNumber creditNumber, Amount amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		Amount balance = creditAccount.getBalance();
		balance = balance.add(amount);
		creditAccount.setBalance(balance);

	}

	public CreditCustomer getCustomerForCredit(Credit credit) {
		CreditCustomer customer = null;
		for (Map.Entry<CustomerNumber, CreditCustomer> entry : customerList.entrySet()) {
			if (entry.getValue().getCreditList().contains(credit)) {
				customer = entry.getValue();
			}
		}
		return customer;
	}

	public CreditAccount newCreditAccount(Credit credit) {
		CreditAccount account = new CreditAccount(credit, accountNumberFactory.newAccountNumber());
		accountList.put(account.getAccountnumber(), account);
		CreditCustomer customer = this.getCustomerForCredit(credit);
		customer.getAccountList().add(account);
		return account;
	}

	public List<CreditAccount> getCreditAccountList() {
		return new ArrayList<>(accountList.values());
	}

	public List<CreditCustomer> getCreditCustomerList() {
		return new ArrayList<>(customerList.values());
	}

	public CreditAccount getCreditAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Set<AccountNumber> getAccountNumberList() {

		return accountList.keySet();
	}

}
