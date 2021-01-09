package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import credit.Credit;
import credit.CreditCustomer;
import valueObjects.Amount;

class CreditTest {

	@Test
	void testCreditConstruction() {
		
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		Credit credit = new Credit(new Amount(1000));
		customer.getCreditList().add(credit);
		assertEquals(new Amount(1000), credit.getAmountOfCredit());
		assertNotNull(credit.getCreditNumber());
		assertTrue(customer.getCreditList().contains(credit));
	}
}
