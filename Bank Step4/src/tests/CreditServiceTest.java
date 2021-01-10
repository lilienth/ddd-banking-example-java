package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import credit.Credit;
import credit.CreditAccount;
import credit.CreditCustomer;
import credit.CreditService;
import valueObjects.Amount;
import valueObjects.CreditNumber;
import credit.Credit.Status;

class CreditServiceTest {


	@Test
	void testCSCreation() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);
		
		assertEquals(5,cs.getCreditCustomerList().size());
		assertEquals(0,cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(new Amount(1000), cs.getCreditCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(new Amount(1000), credit.getAmountOfCredit());
		assertTrue(credit.getStatus()==Status.applied);
		assertEquals(5,cs.getCreditCustomerList().size());
		
		CreditAccount creditAccount = cs.grandCredit(creditNumber);
		assertTrue(credit.getStatus()==Status.granted);
		assertTrue(credit.getAccount()==creditAccount);		
		assertTrue(cs.getCreditAccountList().contains(creditAccount));
		assertEquals(1,cs.getCreditAccountList().size());

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountnumber());
		assertEquals(credit, credit2);
	}
	
	@Test
	void testAMSNewCustomerNewAccount() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);

		assertEquals(5,cs.getCreditCustomerList().size());
		assertEquals(0,cs.getCreditAccountList().size());
		
		CreditCustomer customer = cs.getCreditCustomerList().get(0);		
		CreditNumber creditNumber = cs.applyForCredit(new Amount(1000), customer);
		Credit credit = cs.getCredit(creditNumber);
		CreditAccount newCreditAccount = cs.newCreditAccount(credit);
		assertTrue(cs.getCreditAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, cs.getCreditAccount(newCreditAccount.getAccountnumber()));
		assertEquals(1,cs.getCreditAccountList().size());
		
		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(customer.getAccountList().contains(newCreditAccount));
		
	}
	
	
	@Test
	void testCreditProcess() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);

		assertEquals(5,cs.getCreditCustomerList().size());
		assertEquals(0,cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(new Amount(1000), cs.getCreditCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(5,cs.getCreditCustomerList().size());
		
		CreditAccount creditAccount = cs.grandCredit(creditNumber);
		assertTrue(credit.getStatus()==Status.granted);
		assertTrue(credit.getAccount()==creditAccount);
		assertEquals(1,cs.getCreditAccountList().size());
		assertEquals(new Amount(-1000), creditAccount.getBalance());
		
		cs.makePaymentForCredit(creditNumber, new Amount(100));
		assertEquals(new Amount(-900), creditAccount.getBalance());		
		assertEquals(new Amount(1000), credit.getAmountOfCredit());
	
	}
	

}
