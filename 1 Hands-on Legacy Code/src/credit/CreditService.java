package credit;

import java.util.HashMap;
import java.util.Map;

import accounting.AccountManagementService;
import models.Account;
import credit.Credit.Status;
import models.CreditAccount;
import models.Customer;

public class CreditService {
	private AccountManagementService accountManagementService = null;

	private int creditNumberCounter = 0;
	private Map<Integer, Credit> creditList = new HashMap<Integer, Credit>();

	public CreditService(AccountManagementService accountManagementService) {
		this.accountManagementService = accountManagementService;
	}

	public int applyForCredit(float amount, Customer customer) {

		int creditNumber = creditNumberCounter++;
		Credit credit = new Credit(creditNumber, customer, amount);
		creditList.put(creditNumber, credit);

		return creditNumber;
	}

	public CreditAccount grantCredit(int creditNumber) {
		Credit credit = this.getCredit(creditNumber);
		credit.setStatus(Status.granted);
		CreditAccount newCreditAccount = accountManagementService.newCreditAccount(credit);
		credit.setAccount(newCreditAccount);
		return newCreditAccount;
	}

	public Credit getCredit(int creditNumber) {
		return creditList.get(creditNumber);
	}

	public Credit getCreditFromAccountNumber(int accountNumber) {
		CreditAccount account = (CreditAccount) accountManagementService.getAccount(accountNumber);
		return creditList.get(account.getCredit().getCreditNumber());
	}

	public void makePaymentForCredit(int creditNumber, float amount) {
		Credit credit = creditList.get(creditNumber);
		CreditAccount creditAccount = credit.getAccount();
		float balance = creditAccount.getBalance();
		balance = balance + amount;
		creditAccount.setBalance(balance);

	}

	public void makePaymentForCreditAccount(int accountNumber, float amount) {
		Account account = accountManagementService.getAccount(accountNumber);
		float balance = account.getBalance();
		balance = balance + amount;
		account.setBalance(balance);

	}

}
