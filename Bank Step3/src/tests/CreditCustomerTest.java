package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import credit.Credit;
import credit.CreditAccount;
import credit.CreditCustomer;

class CreditCustomerTest {

	@Test
	void testCustomerConstruction() {
		
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		assertEquals("Carola", customer.getFirstName());
		assertEquals("Lilienthal", customer.getFamilyName());
		assertEquals(LocalDate.of(1967, 9, 11), customer.getDateOfBirth());
		assertEquals(11, customer.getCustomerNumber());
	}

	@Test
	void testCustomerAccount() {
		
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		List<CreditAccount> accountlist = new ArrayList< CreditAccount>();
		customer.setAccountList(accountlist);
		assertEquals(accountlist, customer.getAccountList());
		
		
	}
	
	@Test
	void testCreditAccount() {
		
		CreditCustomer customer = new CreditCustomer("Carola", "Lilienthal", LocalDate.of(1967,9,11), 11);
		List<Credit> creditlist = new ArrayList<Credit>();
		customer.setCreditList(creditlist);
		assertEquals(creditlist, customer.getCreditList());		
		
	}

}
