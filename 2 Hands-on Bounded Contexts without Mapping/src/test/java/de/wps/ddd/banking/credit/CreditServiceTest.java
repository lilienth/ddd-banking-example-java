package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.credit.Credit.Status;

class CreditServiceTest {

	public static CreditService prepareTestData() {
		CreditService cs = new CreditService();
		CreditCustomer customer = cs.newCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 1);
		cs.newCreditAccount(new Credit(1, customer, 1000));
		cs.newCreditAccount(new Credit(1, customer, 5000));
		cs.newCreditAccount(new Credit(1, customer, 2000));

		customer = cs.newCustomer("Hans", "Lilienthal", LocalDate.of(1968, 9, 11), 2);
		cs.newCreditAccount(new Credit(1, customer, 5000));
		cs.newCreditAccount(new Credit(1, customer, 2000));

		customer = cs.newCustomer("Dieter", "Lilienthal", LocalDate.of(1969, 9, 11), 3);
		cs.newCreditAccount(new Credit(1, customer, 5000));
		cs.newCreditAccount(new Credit(1, customer, 3000));

		customer = cs.newCustomer("Franz", "Lilienthal", LocalDate.of(1964, 9, 11), 4);
		cs.newCreditAccount(new Credit(1, customer, 5000));
		cs.newCreditAccount(new Credit(1, customer, 4000));

		customer = cs.newCustomer("Carsten", "Lilienthal", LocalDate.of(1965, 9, 11), 5);
		cs.newCreditAccount(new Credit(1, customer, 5000));

		return cs;
	}

	@Test
	void testCSCreation() {
		CreditService cs = CreditServiceTest.prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(10, cs.getCreditAccountList().size());

		int creditNumber = cs.applyForCredit(1000, cs.getCreditCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(1000, credit.getAmountOfCredit());
		assertTrue(credit.getStatus() == Status.applied);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertTrue(cs.getCreditAccountList().contains(creditAccount));
		assertEquals(11, cs.getCreditAccountList().size());

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountnumber());
		assertEquals(credit, credit2);
	}

	@Test
	void testAMSNewCustomerNewAccount() {
		CreditService cs = CreditServiceTest.prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(10, cs.getCreditAccountList().size());

		CreditCustomer customer = cs.getCreditCustomerList().get(0);
		int creditNumber = cs.applyForCredit(1000, customer);
		Credit credit = cs.getCredit(creditNumber);
		CreditAccount newCreditAccount = cs.newCreditAccount(credit);
		assertTrue(cs.getCreditAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, cs.getCreditAccount(newCreditAccount.getAccountnumber()));
		assertEquals(11, cs.getCreditAccountList().size());

		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(customer.getAccountList().contains(newCreditAccount));

	}

	@Test
	void testCreditProcess() {
		CreditService cs = CreditServiceTest.prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(10, cs.getCreditAccountList().size());

		int creditNumber = cs.applyForCredit(1000, cs.getCreditCustomerList().get(0));
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber);
		assertTrue(credit.getStatus() == Status.granted);
		assertTrue(credit.getAccount() == creditAccount);
		assertEquals(11, cs.getCreditAccountList().size());
		assertEquals(-1000, creditAccount.getBalance());

		cs.makePaymentForCredit(creditNumber, 100);
		assertEquals(-900, creditAccount.getBalance());
		assertEquals(1000, credit.getAmountOfCredit());

	}

}
