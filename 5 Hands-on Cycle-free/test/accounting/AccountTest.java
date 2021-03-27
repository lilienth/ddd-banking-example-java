package accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sharedKernel.Amount;

class AccountTest {

	@Test
	void testAccountConstruction() {

		Account account = new Account();
		assertEquals(1, account.getAccountnumber().value());
		assertEquals(0, account.getBalance().value());
	}

	@Test
	void testBalanceAccount() {
		Account account = new Account();
		assertEquals(0, account.getBalance().value());
		account.deposit(Amount.of(100));
		assertEquals(100, account.getBalance().value());

	}

}
