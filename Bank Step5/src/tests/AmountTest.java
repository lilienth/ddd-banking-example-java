package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import valueObjects.Amount;

class AmountTest {

	@Test
	void testCreation() {
		assertTrue(Amount.isValidAmount(100));
		assertFalse(Amount.isValidAmount(-100));
		assertTrue(Amount.isValidAmount(0));
		assertTrue(Amount.isValidAmount(1));
		assertFalse(Amount.isValidAmount(-1));
		
		Amount amount = new Amount(10);
		assertNotNull(amount);
		assertEquals(10, amount.getAmount());
	}
	
	@Test
	void testAdd() {
		Amount amount1 = new Amount(10);
		Amount amount2 = new Amount(5);		
		assertFalse(amount1.equals(amount2));
		
		Amount amount3 = amount1.add(amount2);
		
		assertEquals(15, amount3.getAmount());
	}
	
	
	@Test
	void testSubstract() {
		Amount amount1 = new Amount(10);
		Amount amount2 = new Amount(5);
		
		Amount amount3 = amount1.subtract(amount2);
		
		assertEquals(5, amount3.getAmount());
		assertTrue(amount2.equals(amount3));
	}

}
