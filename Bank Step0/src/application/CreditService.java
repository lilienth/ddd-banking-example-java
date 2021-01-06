package application;

import models.Credit;
import models.Credit.Status;
import models.CreditAccount;
import models.Customer;

import java.util.HashMap;
import java.util.Map;


public class CreditService {
	private AccountManagementService accountManagementService = null;

	private int creditNumberCounter = 0;
	private Map<Integer, Credit> creditList = new HashMap<Integer, Credit>();
	
	public CreditService(AccountManagementService accountManagementService) {
		this.accountManagementService = accountManagementService;
	}

	public int applyForCredit (float amount, Customer customer) {
		
		int creditNumber = creditNumberCounter++;
		Credit credit = new Credit(amount, customer, creditNumber);
		creditList.put(creditNumber, credit);
		
		return creditNumber;		
	}
	
	public CreditAccount grandCredit (int creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = accountManagementService.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit (int creditNumber) {
		return creditList.get(creditNumber);
	}
	
	public void makePaymentForCredit (int creditNumber, float amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		float balance = creditAccount.getBalance();
		balance = balance + amount;
		creditAccount.setBalance(balance);
		
	}
	
	
}
