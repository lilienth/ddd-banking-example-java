package de.wps.ddd.banking.accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.CustomerNumber;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import de.wps.ddd.banking.sharedKernel.Amount;

class AccountTest {

	public static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of(1);

	public static final CustomerNumber CUSTOMER_NUMBER = new CustomerNumber(2);

	@Test
	void testAccountConstruction() {
		Customer accountOwner = new Customer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(ACCOUNT_NUMBER, accountOwner);
		assertEquals(ACCOUNT_NUMBER, account.getAccountnumber());
		assertEquals(0, account.getBalance().value());
		assertEquals(accountOwner.getCustomerNumber(), account.getAccountOwner().getCustomerNumber());
	}

	@Test
	void testBalanceAccount() {
		Customer accountowner = new Customer(CUSTOMER_NUMBER, "Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		Account account = new Account(ACCOUNT_NUMBER, accountowner);
		assertEquals(0, account.getBalance().value());
		account.deposit(Amount.of(100));
		assertEquals(100, account.getBalance().value());

	}

}
