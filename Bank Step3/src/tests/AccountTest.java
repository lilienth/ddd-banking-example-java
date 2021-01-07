package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import accounting.Account;

class AccountTest {

	@Test
	void testAccountConstruction() {
		
		Account account = new Account(10);
		assertEquals(10, account.getAccountnumber());
		assertEquals(0, account.getBalance());
	}
	
	@Test
	void testBalanceAccount() {
		Account account = new Account(10);
		assertEquals(0, account.getBalance());
		account.withdraw(100);
		assertEquals(-100, account.getBalance());
		account.deposit(200);
		assertEquals(100, account.getBalance());
		
	}

}
