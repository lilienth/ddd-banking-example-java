package de.wps.ddd.banking.accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;


class AccountTest {

	public static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(9);

	@Test
	void testAccountConstruction() {

		Account account = new Account(ACCOUNT_NUMBER);
		assertEquals(ACCOUNT_NUMBER, account.getAccountnumber());
		assertEquals(0, account.getBalance().value());
	}

	@Test
	void testBalanceAccount() {
		Account account = new Account(ACCOUNT_NUMBER);
		assertEquals(0, account.getBalance().value());
		account.deposit(Amount.of(100));
		assertEquals(100, account.getBalance().value());

	}

}
