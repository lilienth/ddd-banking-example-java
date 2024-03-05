package de.wps.ddd.banking.sharedKernel;

import static de.wps.common.contracts.BaseContracts.requireNotNull;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory to create {@link CreditNumber}s.
 */
public class CreditNumberFactory {

    /**
     * Normally this would be backed by some kind of persistence store
     */
    private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(0);

    public CreditNumber newCreditNumber() {
        int nextFreeNumber = NUMBER_COUNTER.incrementAndGet();
        return CreditNumber.of(nextFreeNumber);
    }

    public boolean isKnownCreditNumber(CreditNumber creditNumber) {
        requireNotNull(creditNumber, "creditNumber");
        return creditNumber.valueInt() <= NUMBER_COUNTER.get();
    }
}
