package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import models.Credit;
import models.Customer;

class CreditTest {

	@Test
	void testCreditConstruction() {
		
		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		Credit credit = new Credit(1000, 12);
		customer.getCreditList().add(credit);
		assertEquals(1000, credit.getAmountOfCredit());
		assertEquals(12, credit.getCreditNumber());
		assertTrue(customer.getCreditList().contains(credit));
	}
}
