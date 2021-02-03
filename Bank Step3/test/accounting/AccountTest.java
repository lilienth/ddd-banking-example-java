package accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void testAccountConstruction() {

		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 11);
		Account account = new Account(10, accountowner);
		assertEquals(10, account.getAccountnumber());
		assertEquals(0, account.getBalance());
		assertEquals(accountowner, account.getAccountowner());
	}

	@Test
	void testBalanceAccount() {
		Customer accountowner = new Customer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11), 11);
		Account account = new Account(10, accountowner);
		assertEquals(0, account.getBalance());
		account.setBalance(100);
		assertEquals(100, account.getBalance());

	}

}
