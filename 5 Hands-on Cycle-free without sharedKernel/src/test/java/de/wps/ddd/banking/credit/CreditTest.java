package de.wps.ddd.banking.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreditTest {

    public static final CreditNumber CREDIT_NUMBER = CreditNumber.of(11);

    @Test
    void testCreditConstruction() {

        Credit credit = new Credit(CREDIT_NUMBER, Amount.of(1000));
        assertEquals(Amount.of(1000), credit.getAmountOfCredit());
        assertEquals(CREDIT_NUMBER, credit.getCreditNumber());
    }
}
