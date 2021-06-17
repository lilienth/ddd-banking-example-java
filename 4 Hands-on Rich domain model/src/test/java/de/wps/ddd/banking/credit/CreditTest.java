package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class CreditTest {

	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Credit credit = new Credit(customer, Amount.of(1000));
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
		assertNotNull(credit.getCreditNumber());

		customer.getCreditList().add(credit);
		assertTrue(customer.getCreditList().contains(credit));
		assertTrue(credit.canBeGranted());
		credit.grant(new CreditAccount(credit));
		assertFalse(credit.canBeGranted());
	}
}
