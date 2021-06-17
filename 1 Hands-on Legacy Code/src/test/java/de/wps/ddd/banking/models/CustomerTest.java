package de.wps.ddd.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testCustomerConstruction() {

		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 11);
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertEquals(11, customer.getCustomerNumber());
		assertNotNull(customer.getAccountList());
		assertNotNull(customer.getCreditList());
	}

}
