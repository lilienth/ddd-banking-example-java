package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.CreditNumber;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class CreditTest {

	public static final CustomerNumber CUSTOMER_NUMBER = new CustomerNumber(2);

	public static final CreditNumber CREDIT_NUMBER = CreditNumber.of(7);



	@Test
	void testCreditConstruction() {

		CreditCustomer customer = new CreditCustomer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Credit credit = new Credit(CREDIT_NUMBER, customer, Amount.of(1000));
		assertEquals(Amount.of(1000), credit.getAmountOfCredit());
		assertNotNull(credit.getCreditNumber());

		customer.getCreditList().add(credit);
		assertTrue(customer.getCreditList().contains(credit));
		assertTrue(credit.canBeGranted());
		credit.grant(new CreditAccount(AccountNumber.of(3), credit));
		assertFalse(credit.canBeGranted());
	}
}
