package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import models.Security;

class SecurityTest {

	@Test
	void testSecurityCreation() {
		Security security = new Security("12345678AB", 1000, LocalDate.of(2019,2,2));
		assertEquals("12345678AB", security.getISIN());
		assertEquals(1000, security.getPurchasePrice());
		assertEquals(LocalDate.of(2019,2,2), security.getPurchaseDate());
	}


}
