package de.wps.ddd.banking.sharedKernel;

import static de.wps.common.contracts.BaseContracts.requireNotNull;

import java.util.concurrent.atomic.AtomicInteger;
import org.jmolecules.ddd.annotation.Factory;

/**
 * Factory to create {@link CustomerNumber}s.
 */
@Factory
public class CustomerNumberFactory {

    /**
     * Normally this would be backed by some kind of persistence store
     */
    private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(0);

    public CustomerNumber newCustomerNumber() {
        int nextFreeNumber = NUMBER_COUNTER.incrementAndGet();
        return new CustomerNumber(nextFreeNumber);
    }

    public boolean isKnownCustomerNumber(CustomerNumber CustomerNumber) {
        requireNotNull(CustomerNumber, "CustomerNumber");
        return CustomerNumber.customerNumberValue() <= NUMBER_COUNTER.get();
    }
}
