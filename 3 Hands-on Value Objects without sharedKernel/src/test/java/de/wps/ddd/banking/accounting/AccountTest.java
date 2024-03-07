package de.wps.ddd.banking.accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class AccountTest {

	public static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(1);
	public static final CustomerNumber CUSTOMER_NUMBER = new CustomerNumber(1);

	@Test
	void testAccountConstruction() {

		Customer accountowner = new Customer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountowner, ACCOUNT_NUMBER);
		assertEquals(ACCOUNT_NUMBER, account.getAccountnumber());
		assertEquals(0, account.getBalance().value());
		assertEquals(accountowner, account.getAccountowner());
	}

	@Test
	void testBalanceAccount() {
		Customer accountOwner = new Customer(CUSTOMER_NUMBER,"Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountOwner, ACCOUNT_NUMBER);
		assertEquals(0, account.getBalance().value());
		account.setBalance(Amount.of(100));
		assertEquals(100, account.getBalance().value());

	}

}
