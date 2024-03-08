package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.accounting.AccountManagementServiceTest;
import de.wps.ddd.banking.credit.Credit.Status;
import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CreditNumber;

class CreditServiceTest {

	@Test
	void testCSCreation() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), cs.getCreditCustomerList().getFirst());
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
        assertSame(credit.getStatus(), Status.applied);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
        assertSame(credit.getStatus(), Status.granted);
        assertEquals(credit.getAccount().orElseThrow(), creditAccount);
		assertTrue(cs.getCreditAccountList().contains(creditAccount));
		assertEquals(1, cs.getCreditAccountList().size());

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountNumber());
		assertEquals(credit, credit2);
	}

	@Test
	void testAMSNewCustomerNewAccount() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditCustomer customer = cs.getCreditCustomerList().getFirst();
		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), customer);
		Credit credit = cs.getCredit(creditNumber);
		CreditAccount newCreditAccount = cs.newCreditAccount(credit);
		assertTrue(cs.getCreditAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, cs.getCreditAccount(newCreditAccount.getAccountNumber()));
		assertEquals(1, cs.getCreditAccountList().size());

		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountNumber()));
		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountNumber()));
		assertTrue(customer.hasAccount(newCreditAccount.getAccountNumber()));

	}

	@Test
	void testCreditProcess() {
		CreditService cs = new CreditService();
		AccountManagementServiceTest.prepareTestData(cs);

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), cs.getCreditCustomerList().getFirst());
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
        assertSame(credit.getStatus(), Status.granted);
        assertEquals(credit.getAccount().orElseThrow(), creditAccount);
		assertEquals(1, cs.getCreditAccountList().size());
		assertEquals(Amount.of(-1000), creditAccount.getBalance());

		cs.makePaymentForCredit(creditNumber, Amount.of(100));
		assertEquals(Amount.of(-900), creditAccount.getBalance());
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());

	}

}
