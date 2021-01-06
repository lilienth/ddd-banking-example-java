package tests;

import static org.junit.jupiter.api.Assertions.*;


import application.AccountManagementService;
import application.CreditService;
import models.Credit;
import models.Credit.Status;
import models.CreditAccount;

import org.junit.jupiter.api.Test;

class CreditServiceTest {

	private AccountManagementService ams;
	private CreditService cs;

	@Test
	void testCSCreation() {
		ams = new AccountManagementService();
		cs = new CreditService(ams);

		int creditNumber = cs.applyForCredit(1000, ams.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(1000, credit.getAmountOfCredit());
		assertTrue(credit.getStatus()==Status.applied);
		
		CreditAccount creditAccount = cs.grandCredit(creditNumber);
		assertTrue(credit.getStatus()==Status.granted);
		assertTrue(credit.getAccount()==creditAccount);		
		assertTrue(ams.getAccountList().contains(creditAccount));
		assertTrue(ams.getCustomerList().contains(creditAccount.getAccountowner()));

	}
	
	@Test
	void testCreditProcess() {
		ams = new AccountManagementService();
		cs = new CreditService(ams);

		int creditNumber = cs.applyForCredit(1000, ams.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		
		CreditAccount creditAccount = cs.grandCredit(creditNumber);
		assertTrue(credit.getStatus()==Status.granted);
		assertTrue(credit.getAccount()==creditAccount);
		assertEquals(-1000, creditAccount.getBalance());
		
		cs.makePaymentForCredit(creditNumber, 100);
		assertEquals(-900, creditAccount.getBalance());		
		assertEquals(1000, credit.getAmountOfCredit());
	
	}
	

}
