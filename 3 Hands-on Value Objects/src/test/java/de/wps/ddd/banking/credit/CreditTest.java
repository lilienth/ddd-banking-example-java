package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.wps.ddd.banking.sharedKernel.CreditNumber;
import de.wps.ddd.banking.sharedKernel.CreditNumberFactory;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class CreditTest {

	private final static CreditNumber CREDIT_NUMBER = new CreditNumberFactory().newCreditNumber();
	public static final CustomerNumber CUSTOMER_NUMBER = new CustomerNumber(1);

	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Credit credit = new Credit(customer, CREDIT_NUMBER, Amount.of(1000));
		assertEquals(CREDIT_NUMBER, credit.getCreditNumber());
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
		assertNotNull(credit.getCreditNumber());

		customer.getCreditList().add(credit);
		assertTrue(customer.getCreditList().contains(credit));
	}
}
