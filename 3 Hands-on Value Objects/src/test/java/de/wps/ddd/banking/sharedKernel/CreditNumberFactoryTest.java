package de.wps.ddd.banking.sharedKernel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreditNumberFactoryTest {

    @Test
    void isKnownCreditNumber() {
        CreditNumberFactory objectUnderTest = new CreditNumberFactory();

        CreditNumber creditNumber = objectUnderTest.newCreditNumber();
        assertNotNull(creditNumber);
        assertTrue(objectUnderTest.isKnownCreditNumber(creditNumber));

        int maxCreditNumber = creditNumber.valueInt();
        assertFalse(objectUnderTest.isKnownCreditNumber(CreditNumber.of(maxCreditNumber + 1)));
    }
}