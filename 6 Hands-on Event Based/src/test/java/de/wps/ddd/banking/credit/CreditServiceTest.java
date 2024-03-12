package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.credit.Credit.Status;

class CreditServiceTest {

	public static final CreditAccountNumber ACCOUNT_NUMBER = CreditAccountNumber.of(5);

	public static CreditService prepareTestData() {
		CreditService creditService = new CreditService();
		creditService.newCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), new CustomerNumber(1));
		creditService.newCustomer("Hans", "Lilienthal", LocalDate.of(1968, 9, 11), new CustomerNumber(2));
		creditService.newCustomer("Dieter", "Lilienthal", LocalDate.of(1969, 9, 11), new CustomerNumber(3));
		creditService.newCustomer("Franz", "Lilienthal", LocalDate.of(1964, 9, 11), new CustomerNumber(4));
		creditService.newCustomer("Carsten", "Lilienthal", LocalDate.of(1965, 9, 11), new CustomerNumber(5));
		return creditService;
	}
	@Test
	void testCSCreation() {
		CreditService cs = prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), cs.getCreditCustomerList().getFirst());
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
        assertSame(credit.getStatus(), Status.applied);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber, ACCOUNT_NUMBER);
        assertSame(credit.getStatus(), Status.granted);
        assertEquals(credit.getAccount().orElseThrow(), creditAccount);
		assertTrue(cs.getCreditAccountList().contains(creditAccount));
		assertEquals(1, cs.getCreditAccountList().size());

		Credit credit2 = cs.getCreditFromAccountNumber(creditAccount.getAccountNumber());
		assertEquals(credit, credit2);
	}

	@Test
	void testAMSNewCustomerNewAccount() {
		CreditService cs = prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditCustomer customer = cs.getCreditCustomerList().getFirst();
		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), customer);
		Credit credit = cs.getCredit(creditNumber);
		CreditAccount newCreditAccount = cs.newCreditAccount(ACCOUNT_NUMBER, credit);
		assertTrue(cs.getCreditAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, cs.getCreditAccount(newCreditAccount.getAccountNumber()));
		assertEquals(1, cs.getCreditAccountList().size());

		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountNumber()));
		assertTrue(cs.getAccountNumberList().contains(newCreditAccount.getAccountNumber()));
		assertTrue(customer.hasAccount(newCreditAccount.getAccountNumber()));

	}

	@Test
	void testCreditProcess() {
		CreditService cs = prepareTestData();

		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());

		CreditNumber creditNumber = cs.applyForCredit(Amount.of(1000), cs.getCreditCustomerList().getFirst());
		Credit credit = cs.getCredit(creditNumber);
		assertEquals(5, cs.getCreditCustomerList().size());

		CreditAccount creditAccount = cs.grantCredit(creditNumber, ACCOUNT_NUMBER);
        assertSame(credit.getStatus(), Status.granted);
        assertEquals(credit.getAccount().orElseThrow(), creditAccount);
		assertEquals(1, cs.getCreditAccountList().size());
		assertEquals(Amount.of(-1000), creditAccount.getBalance());

		cs.makePaymentForCredit(creditNumber, Amount.of(100));
		assertEquals(Amount.of(-900), creditAccount.getBalance());
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());

	}

}
