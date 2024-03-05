package de.wps.ddd.banking.sharedKernel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreditNumberTest {

    @Test
    void of() {
        CreditNumber creditNumber = CreditNumber.of(5);
        assertNotNull(creditNumber);
        assertEquals(5, creditNumber.valueInt());
    }
    @Test
    void ofInvalid() {
        assertThrows(IllegalArgumentException.class, () -> CreditNumber.of(0));
        assertThrows(IllegalArgumentException.class, () -> CreditNumber.of(-1));
    }

    @Test
    void testEquals() {
        CreditNumber creditNumber1 = CreditNumber.of(1);
        CreditNumber creditNumber2 = CreditNumber.of(1);
        CreditNumber creditNumber3 = CreditNumber.of(2);

        assertTrue(creditNumber1.equals(creditNumber1));
        assertEquals(creditNumber1, creditNumber2);
        assertEquals(creditNumber2, creditNumber1);
        assertNotEquals(creditNumber1, creditNumber3);
    }
}