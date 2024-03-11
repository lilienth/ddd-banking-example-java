package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditCustomerTest {

	public static final CustomerNumber CUSTOMER_NUMBER = new CustomerNumber(2);

	@Test
	void testCustomerConstruction() {

		CreditCustomer customer = new CreditCustomer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertEquals(CUSTOMER_NUMBER, customer.getCustomerNumber());
	}

}
