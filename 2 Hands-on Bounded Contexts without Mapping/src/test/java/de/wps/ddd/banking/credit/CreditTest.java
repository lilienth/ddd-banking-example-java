package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditTest {

	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 11);
		Credit credit = new Credit(12, customer, 1000);
		customer.getCreditList().add(credit);
		assertEquals(1000, credit.getAmountOfCredit());
		assertEquals(12, credit.getCreditNumber());
		assertTrue(customer.getCreditList().contains(credit));
	}
}
