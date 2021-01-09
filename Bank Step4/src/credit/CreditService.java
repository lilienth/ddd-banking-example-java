package credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import credit.Credit.Status;
import valueObjects.AccountNumber;
import valueObjects.Amount;



public class CreditService {
	private Map<Integer, CreditCustomer> customerList = new HashMap<Integer, CreditCustomer>();
	private Map<AccountNumber, CreditAccount> accountList = new HashMap<AccountNumber, CreditAccount>();
	private Map<Integer, Credit> creditList = new HashMap<Integer, Credit>();
	private int creditNumberCounter = 0;
	
	public CreditService() {
	}

	// should only be called by AccountManagementService
	public void newCustomer(String firstName, String familyName, LocalDate dateOfBirth, int customerNumber) {
		customerList.put(customerNumber, new CreditCustomer(firstName, familyName, dateOfBirth, customerNumber));
		
	}
	
	public CreditAccount newCreditAccount(Amount balance, CreditCustomer customer) {		
		CreditAccount account = new CreditAccount(balance);	
		account.deposit(balance);
		accountList.put(account.getAccountnumber(), account);
		customer.getAccountList().add(account);
		return account;
	}
	public int applyForCredit (Amount amount, CreditCustomer customer) {
		
		int creditNumber = creditNumberCounter++;
		Credit credit = new Credit(amount);
		customer.getCreditList().add(credit);
		creditList.put(creditNumber, credit);
		
		return creditNumber;		
	}
	
	public CreditAccount grandCredit (int creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = this.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit (int creditNumber) {
		return creditList.get(creditNumber);
	}

	
	public Credit getCreditFromAccountNumber (AccountNumber accountNumber) {
		Credit credit = null;
		for (Map.Entry<Integer, Credit> entry : creditList.entrySet()) {
			if (entry.getValue().getAccount().getAccountnumber() == accountNumber)
			{
				credit = entry.getValue();
			}
		}
		return credit;
	}
	
	
	public void makePaymentForCredit (int creditNumber, Amount amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		creditAccount.deposit(amount);
		
	}
	
	public CreditCustomer getCustomerForCredit(Credit credit) {
		CreditCustomer customer = null;
		for (Map.Entry<Integer, CreditCustomer> entry : customerList.entrySet()) {
			if (entry.getValue().getCreditList().contains(credit))
			{
				customer = entry.getValue();
			}			
		}
		return customer;
	}
	
	public CreditAccount newCreditAccount(Credit credit) {		
		CreditAccount account = new CreditAccount(credit.getAmountOfCredit());		
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
