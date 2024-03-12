package de.wps.ddd.banking.accounting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.wps.ddd.banking.accountingevents.NewCustomerRegisteredEvent;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class AccountManagementServiceTest {

	public static AccountManagementService prepareTestData(Consumer<NewCustomerRegisteredEvent> eventBus) {
		CustomerRegistrationEventPublisher publisher = new CustomerRegistrationEventPublisher(eventBus);
		AccountManagementService ams = new AccountManagementService(publisher);
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
		EventConsumer consumer = new EventConsumer();
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(consumer);
		assertNotNull(ams.getAccountList());
		assertNotNull(ams.getCustomerList());
		assertEquals(5, ams.getCustomerList().size());
		assertEquals(10, ams.getAccountList().size());
		assertEquals(5, consumer.eventsPublished.size());

	}

	@Test
	void testAMSTransferMoney() {
		EventConsumer consumer = new EventConsumer();
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(consumer);

		Set<AccountNumber> accountNumbers = ams.getAccountNumberList();
		Iterator<AccountNumber> iterator = accountNumbers.iterator();
		AccountNumber debitorAccountNumber = iterator.next();
		AccountNumber creditorAccountNumber = iterator.next();
		Amount debitorSaldo = ams.getAccount(debitorAccountNumber).getBalance();
		Amount creditorSaldo = ams.getAccount(creditorAccountNumber).getBalance();
		ams.transferMoney(Amount.of(100), debitorAccountNumber, creditorAccountNumber);
		assertEquals(debitorSaldo.subtract(Amount.of(100)), ams.getAccount(debitorAccountNumber).getBalance());
		assertEquals(creditorSaldo.add(Amount.of(100)), ams.getAccount(creditorAccountNumber).getBalance());

	}

	@Test
	void testAMSNewCustomerNewAccount() {
		EventConsumer consumer = new EventConsumer();
		AccountManagementService ams = AccountManagementServiceTest.prepareTestData(consumer);

		Customer newCustomer = ams.newCustomer("Tea", "Ginster", LocalDate.of(1950, 12, 2));
		assertTrue(ams.getCustomerList().contains(newCustomer));
		assertEquals(6, ams.getCustomerList().size());
		assertEquals(6, consumer.eventsPublished.size());

		Account newAccount = ams.newAccount(Amount.of(2000), newCustomer);
		assertTrue(ams.getAccountList().contains(newAccount));
		assertEquals(newAccount, ams.getAccount(newAccount.getAccountnumber()));
		assertEquals(newCustomer, ams.getCustomer(newAccount.getAccountnumber()));
		assertTrue(newCustomer.hasAccount(newAccount.getAccountnumber()));
		assertEquals(11, ams.getAccountList().size());

		assertTrue(ams.getAccountNumberList().contains(newAccount.getAccountnumber()));

	}

	static class EventConsumer implements Consumer<NewCustomerRegisteredEvent> {
		final List<NewCustomerRegisteredEvent> eventsPublished = new LinkedList<>();

		@Override
		public void accept(NewCustomerRegisteredEvent newCustomerRegisteredEvent) {
			eventsPublished.add(newCustomerRegisteredEvent);
		}
	}

}
