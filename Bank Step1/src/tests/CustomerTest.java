package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Account;
import models.Credit;
import models.Customer;

class CustomerTest {

	@Test
	void testCustomerConstruction() {
		
		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertEquals(11, customer.getCustomerNumber());
	}

	@Test
	void testCustomerAccount() {
		
		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		List<Account> accountlist = new ArrayList<Account>();
		customer.setAccountList(accountlist);
		assertEquals(accountlist, customer.getAccountList());
		
		
	}
	
	@Test
	void testCreditAccount() {
		
		Customer customer = new Customer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		List<Credit> creditlist = new ArrayList<Credit>();
		customer.setCreditList(creditlist);
		assertEquals(creditlist, customer.getCreditList());
		
		
	}

}
