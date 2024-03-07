package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditTest {

	public static final CreditNumber CREDIT_NUMBER = CreditNumber.of(7);

	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11),
				new CustomerNumber(5));
		Credit credit = new Credit(CREDIT_NUMBER, customer, Amount.of(1000));
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
		assertEquals(CREDIT_NUMBER, credit.getCreditNumber());

		customer.getCreditList().add(credit);
		assertTrue(customer.getCreditList().contains(credit));
	}
}
