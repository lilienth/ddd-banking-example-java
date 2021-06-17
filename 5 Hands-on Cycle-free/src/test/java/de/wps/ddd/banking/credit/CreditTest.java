package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class CreditTest {

	@Test
	void testCreditConstruction() {

		Credit credit = new Credit(Amount.of(1000));
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
		assertNotNull(credit.getCreditNumber());
	}
}
