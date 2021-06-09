package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import credit.Credit;
import org.junit.jupiter.api.Test;

class CreditTest {

	@Test
	void testCreditConstruction() {

		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 11);
		Credit credit = new Credit(12, customer, 1000);
		assertEquals(1000, credit.getAmountOfCredit());
		assertEquals(customer, credit.getCustomer());
		assertEquals(12, credit.getCreditNumber());
		assertTrue(customer.getCreditList().contains(credit));
	}
}
