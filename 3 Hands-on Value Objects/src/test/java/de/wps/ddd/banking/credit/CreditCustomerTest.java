package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditCustomerTest {

	@Test
	void testCustomerConstruction() {

		CreditCustomer customer = new CreditCustomer(new CustomerNumber(1), "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertNotNull(customer.getCustomerNumber());
		assertNotNull(customer.getAccountList());
	}

}
