package de.wps.ddd.banking.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.models.Credit;
import de.wps.ddd.banking.models.Credit.Status;
import de.wps.ddd.banking.models.CreditAccount;

class CreditServiceTest {

	@Test
	void testCSCreation() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData();
		CreditService cs = new CreditService(ams);

		int creditNumber = cs.applyForCredit(1000, ams.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(1000, credit.getAmountOfCredit());
		assertTrue(credit.getStatus() == Status.applied);

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertEquals(credit, creditAccount.getCredit());
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertTrue(ams.getAccountList().contains(creditAccount));
		assertTrue(ams.getCustomerList().contains(creditAccount.getAccountowner()));
		assertEquals(11, ams.getAccountList().size());
		assertEquals(credit, creditAccount.getCredit());

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountnumber());
		assertEquals(credit, credit2);

	}

	@Test
	void testCreditProcess() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData();
		CreditService cs = new CreditService(ams);

		int creditNumber = cs.applyForCredit(1000, ams.getCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertEquals(credit, creditAccount.getCredit());
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertEquals(-1000, creditAccount.getBalance());
		assertEquals(11, ams.getAccountList().size());

		cs.makePaymentForCredit(creditNumber, 100);
		assertEquals(-900, creditAccount.getBalance());
		assertEquals(1000, credit.getAmountOfCredit());

	}

}
