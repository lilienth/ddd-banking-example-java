package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import application.AccountManagementService;
import models.Account;
import models.Credit;
import models.CreditAccount;
import models.Customer;

import org.junit.jupiter.api.Test;

class AccountManagementServiceTest {

	private AccountManagementService ams;

	@Test
	void testAMSCreation() {
		ams = new AccountManagementService();
		assertNotNull(ams.getAccountList());
		assertNotNull(ams.getCustomerList());
		assertEquals(5,ams.getCustomerList().size());
		assertEquals(10,ams.getAccountList().size());
	}
	
	@Test
	void testAMSTransferMoney() {		
		ams = new AccountManagementService();
		Set<Integer> accountNumbers = ams.getAccountNumberList();
		Iterator<Integer> iterator = accountNumbers.iterator();
		int debitorAccountNumber = iterator.next();
		int creditorAccountNumber = iterator.next();
		float debitorSaldo = ams.getAccount(debitorAccountNumber).getBalance();
		float creditorSaldo = ams.getAccount(creditorAccountNumber).getBalance();
		ams.transferMoney(100, debitorAccountNumber, creditorAccountNumber);
		assertEquals(debitorSaldo-100, ams.getAccount(debitorAccountNumber).getBalance());
		assertEquals(creditorSaldo+100, ams.getAccount(creditorAccountNumber).getBalance());
		
	}
	
	@Test
	void testAMSNewCustomerNewAccount() {
		ams = new AccountManagementService();
		
		Customer newCustomer = ams.newCustomer("Tea", "Ginster", LocalDate.of(1950, 12, 2));
		assertTrue(ams.getCustomerList().contains(newCustomer));

		Account newAccount = ams.newAccount(2000, newCustomer);
		assertTrue(ams.getAccountList().contains(newAccount));
		assertEquals(newAccount, ams.getAccount(newAccount.getAccountnumber()));
		assertTrue(newCustomer.getAccountList().contains(newAccount));
		
		Credit credit = new Credit(1000, 10);
		newCustomer.getCreditList().add(credit);
		CreditAccount newCreditAccount = ams.newCreditAccount(credit);
		assertTrue(ams.getAccountList().contains(newCreditAccount));
		assertEquals(newCreditAccount, ams.getAccount(newCreditAccount.getAccountnumber()));
		
		assertTrue(ams.getAccountNumberList().contains(newAccount.getAccountnumber()));
		assertTrue(ams.getAccountNumberList().contains(newCreditAccount.getAccountnumber()));
		assertTrue(newCustomer.getAccountList().contains(newCreditAccount));
		
	}

}
