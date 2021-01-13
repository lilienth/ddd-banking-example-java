package tests;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import accounting.Account;
import valueObjects.Amount;

class AccountTest {

	@Test
	void testAccountConstruction() {
		
		Account account = new Account();
		assertEquals(new Amount(0), account.getBalance());
		assertNotNull(account.getAccountnumber());
	}
	
	@Test
	void testBalanceAccount() {
		Account account = new Account();
		assertEquals(new Amount(0), account.getBalance());
		account.withdraw(new Amount(100));
		assertEquals(new Amount(-100), account.getBalance());
		account.deposit(new Amount(200));
		assertEquals(new Amount(100), account.getBalance());
		
	}

}
