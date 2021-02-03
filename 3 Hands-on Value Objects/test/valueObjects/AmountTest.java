package valueObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AmountTest {

	@Test
	void testCreation() {
		assertTrue(Amount.isValidAmount(100));
		assertFalse(Amount.isValidAmount(-100));
		assertTrue(Amount.isValidAmount(0));
		assertTrue(Amount.isValidAmount(1));
		assertFalse(Amount.isValidAmount(-1));

		Amount amount = Amount.of(10);
		assertNotNull(amount);
		assertEquals(10, amount.getAmount());
	}

	@Test
	void testAdd() {
		Amount amount1 = Amount.of(10);
		Amount amount2 = Amount.of(5);
		assertFalse(amount1.equals(amount2));

		Amount amount3 = amount1.add(amount2);

		assertEquals(15, amount3.getAmount());
	}

	@Test
	void testSubstract() {
		Amount amount1 = Amount.of(10);
		Amount amount2 = Amount.of(5);

		Amount amount3 = amount1.subtract(amount2);

		assertEquals(5, amount3.getAmount());
		assertTrue(amount2.equals(amount3));
	}

}
