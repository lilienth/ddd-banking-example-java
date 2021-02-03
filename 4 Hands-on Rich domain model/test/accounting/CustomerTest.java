package accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testCustomerConstruction() {

		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertNotNull(customer.getCustomerNumber());
	}

}
