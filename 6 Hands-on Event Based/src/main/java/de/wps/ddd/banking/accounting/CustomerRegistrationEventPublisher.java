package de.wps.ddd.banking.accounting;

import de.wps.ddd.banking.accountingevents.NewCustomerRegisteredEvent;
import java.util.function.Consumer;

public class CustomerRegistrationEventPublisher {

    private final Consumer<NewCustomerRegisteredEvent> eventBus;

    public CustomerRegistrationEventPublisher() {
        this(e -> {});
    }

    CustomerRegistrationEventPublisher(Consumer<NewCustomerRegisteredEvent> eventBus) {
        this.eventBus = eventBus;
    }


    public void newCustomerRegistered(Customer customer) {

        eventBus.accept(new NewCustomerRegisteredEvent(customer.getCustomerNumber().customerNumberValue(),
                customer.getFirstName(),
                customer.getFamilyName(),
                customer.getDateOfBirth()));
    }
}
