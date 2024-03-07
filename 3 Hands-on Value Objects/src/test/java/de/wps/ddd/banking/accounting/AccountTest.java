package de.wps.ddd.banking.accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.AccountNumberFactory;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class AccountTest {

	private final static AccountNumber ACCOUNT_NUMBER = new AccountNumberFactory().newAccountNumber();

	@Test
	void testAccountConstruction() {
		Customer accountOwner = new Customer(new CustomerNumber(1), "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountOwner, ACCOUNT_NUMBER);
		assertEquals(ACCOUNT_NUMBER, account.getAccountnumber());
		assertEquals(0, account.getBalance().value());
		assertEquals(accountOwner, account.getAccountowner());
	}

	@Test
	void testBalanceAccount() {
		Customer accountOwner = new Customer(new CustomerNumber(1), "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(accountOwner, ACCOUNT_NUMBER);
		assertEquals(0, account.getBalance().value());
		account.setBalance(Amount.of(100));
		assertEquals(100, account.getBalance().value());
	}
}
