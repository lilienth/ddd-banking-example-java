package accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

import credit.CreditService;
import sharedKernel.AccountNumber;
import sharedKernel.Amount;

public class AccountManagementServiceTest {

	public static AccountManagementService prepareTestData(CreditService cs) {
		AccountManagementService ams = new AccountManagementService(cs);
		Customer customer = ams.newCustomer("Carola", "Lilienthal", LocalDate.of(1967, 9, 11));
		ams.newAccount(Amount.of(1000), customer);
		ams.newAccount(Amount.of(5000), customer);
		ams.newAccount(Amount.of(2000), customer);

		customer = ams.newCustomer("Hans", "Lilienthal", LocalDate.of(1968, 9, 11));
		ams.newAccount(Amount.of(3000), customer);
		ams.newAccount(Amount.of(5000), customer);

		customer = ams.newCustomer("Dieter", "Lilienthal", LocalDate.of(1969, 9, 11));
		ams.newAccount(Amount.of(3000), customer);
		ams.newAccount(Amount.of(5000), customer);

		customer = ams.newCustomer("Franz", "Lilienthal", LocalDate.of(1964, 9, 11));
		ams.newAccount(Amount.of(4000), customer);
		ams.newAccount(Amount.of(5000), customer);

		customer = ams.newCustomer("Carsten", "Lilienthal", LocalDate.of(1965, 9, 11));
		ams.newAccount(Amount.of(5000), customer);

		return ams;
	}

	@Test
	void testAMSCreation() {
		CreditService cs = new CreditService();
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(cs);
		assertNotNull(ams.getAccountList());
		assertNotNull(ams.getCustomerList());
		assertEquals(5, ams.getCustomerList().size());
		assertEquals(10, ams.getAccountList().size());
		assertEquals(5, cs.getCreditCustomerList().size());
		assertEquals(0, cs.getCreditAccountList().size());
	}

	@Test
	void testAMSTransferMoney() {
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(new CreditService());

		Set<AccountNumber> accountNumbers = ams.getAccountNumberList();
		Iterator<AccountNumber> iterator = accountNumbers.iterator();
		AccountNumber debitorAccountNumber = iterator.next();
		AccountNumber creditorAccountNumber = iterator.next();
		Account debitorAccount = ams.getAccount(debitorAccountNumber);
		debitorAccount.deposit(Amount.of(3000));
		Amount debitorSaldo = debitorAccount.getBalance();
		Amount creditorSaldo = ams.getAccount(creditorAccountNumber).getBalance();
		ams.transferMoney(Amount.of(100), debitorAccountNumber, creditorAccountNumber);
		assertEquals(debitorSaldo.subtract(Amount.of(100)), ams.getAccount(debitorAccountNumber).getBalance());
		assertEquals(creditorSaldo.add(Amount.of(100)), ams.getAccount(creditorAccountNumber).getBalance());

	}

	@Test
	void testAMSNewCustomerNewAccount() {
		CreditService cs = new CreditService();
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(cs);

		Customer newCustomer = ams.newCustomer("Tea", "Ginster", LocalDate.of(1950, 12, 2));
		assertTrue(ams.getCustomerList().contains(newCustomer));
		assertEquals(6, ams.getCustomerList().size());
		assertEquals(6, cs.getCreditCustomerList().size());

		Account newAccount = ams.newAccount(Amount.of(2000), newCustomer);
		assertTrue(ams.getAccountList().contains(newAccount));
		assertEquals(newAccount, ams.getAccount(newAccount.getAccountnumber()));
		assertEquals(newCustomer, ams.getCustomer(newAccount.getAccountnumber()));
		assertTrue(newCustomer.hasAccount(newAccount.getAccountnumber()));
		assertEquals(11, ams.getAccountList().size());

		assertTrue(ams.getAccountNumberList().contains(newAccount.getAccountnumber()));

	}

}
