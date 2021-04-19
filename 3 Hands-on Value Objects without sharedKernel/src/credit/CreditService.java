package credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import credit.Credit.Status;

public class CreditService {
	private Map<CustomerNumber, CreditCustomer> customerList = new HashMap<CustomerNumber, CreditCustomer>();
	private Map<AccountNumber, CreditAccount> accountList = new HashMap<AccountNumber, CreditAccount>();
	private Map<CreditNumber, Credit> creditList = new HashMap<CreditNumber, Credit>();

	public CreditService() {
	}

	// should only be called by AccountManagementService
	public void newCustomer(String firstName, String familyName, LocalDate dateOfBirth, int customerNumber) {
		CustomerNumber number = CustomerNumber.getValidCustomerNumber(customerNumber);
		customerList.put(number, new CreditCustomer(firstName, familyName, dateOfBirth, number));

	}

	public CreditAccount newCreditAccount(Amount balance, Credit credit) {
		CreditAccount account = new CreditAccount(credit);
		account.setBalance(balance);
		accountList.put(account.getAccountnumber(), account);
		CreditCustomer customer = credit.getCustomer();
		customer.getAccountList().add(account);
		return account;
	}

	public CreditNumber applyForCredit(Amount amount, CreditCustomer customer) {

		Credit credit = new Credit(customer, amount);
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
			if (entry.getValue().getAccount().getAccountnumber() == accountNumber) {
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
		CreditAccount account = new CreditAccount(credit);
		accountList.put(account.getAccountnumber(), account);
		CreditCustomer customer = this.getCustomerForCredit(credit);
		customer.getAccountList().add(account);
		return account;
	}

	public List<CreditAccount> getCreditAccountList() {
		return new ArrayList<CreditAccount>(accountList.values());
	}

	public List<CreditCustomer> getCreditCustomerList() {
		return new ArrayList<CreditCustomer>(customerList.values());
	}

	public CreditAccount getCreditAccount(AccountNumber accountNumber) {
		return accountList.get(accountNumber);
	}

	public Set<AccountNumber> getAccountNumberList() {

		return accountList.keySet();
	}

}
