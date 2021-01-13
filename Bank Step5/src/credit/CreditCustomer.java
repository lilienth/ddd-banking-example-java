package credit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import credit.Credit;
import valueObjects.AccountNumber;
import valueObjects.CreditNumber;
import valueObjects.CustomerNumber;

public class CreditCustomer {
	private String firstName;
	private String familyName;
	private LocalDate dateOfBirth;
	private CustomerNumber customerNumber;
	private List<CreditAccount> accountList;
	private List<Credit> creditList;
	
	public CreditCustomer(String firstName, String familyName, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.customerNumber = new CustomerNumber();
		accountList = new ArrayList<CreditAccount>();
		creditList = new ArrayList<Credit>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public CustomerNumber getCustomerNumber() {
		return customerNumber;
	}
		

	public void addAccount(CreditAccount account) {
		accountList.add(account);
	}
	
	public boolean hasAccount(AccountNumber accountNumber) {
		boolean returnValue = false;
		Iterator<CreditAccount> iterator = accountList.iterator();
		while (iterator.hasNext() && returnValue == false) {
			CreditAccount account = iterator.next();
			if (account.getAccountnumber() == accountNumber)
			{
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	public void addCredit(Credit credit) {
		creditList.add(credit);
	}

	public boolean hasCredit(CreditNumber creditNumber) {
		boolean returnValue = false;
		Iterator<Credit> iterator = creditList.iterator();
		while (iterator.hasNext() && returnValue == false) {
			Credit credit = iterator.next();
			if (credit.getCreditNumber() == creditNumber)
			{
				returnValue = true;
			}
		}
		return returnValue;
	}
}
