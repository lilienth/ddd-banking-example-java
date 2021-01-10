package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import accounting.Customer;

class CustomerTest {

	@Test
	void testCustomerConstruction() {
		
		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967,9,11));
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertNotNull(customer.getCustomerNumber());
		assertNotNull(customer.getAccountList());
	}
	
}
