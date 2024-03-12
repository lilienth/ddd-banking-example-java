package de.wps.ddd.banking.accounting;

import static de.wps.common.contracts.BaseContracts.requireNotNull;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory to create {@link AccountNumber}s.
 */
public class AccountNumberFactory {

    /**
     * Normally this would be backed by some kind of persistence store
     */
    private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(0);

    public AccountNumber newAccountNumber() {
        int nextFreeNumber = NUMBER_COUNTER.incrementAndGet();
        return AccountNumber.of(nextFreeNumber);
    }

    public boolean isKnownAccountNumber(AccountNumber accountNumber) {
        requireNotNull(accountNumber, "accountNumber");
        return accountNumber.valueInt() <= NUMBER_COUNTER.get();
    }
}
