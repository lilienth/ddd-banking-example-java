package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import credit.Credit;
import credit.CreditCustomer;
import valueObjects.Amount;

class CreditCustomerTest {

	@Test
	void testCustomerConstruction() {
		
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11));
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertNotNull(customer.getCustomerNumber());
	}

	@Test
	void testCustomerAdd() {
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11));
		
		Credit credit = new Credit(new Amount(1000));
		customer.addCredit(credit);
		assertTrue(customer.hasCredit(credit.getCreditNumber()));
	}

}
