package de.wps.ddd.banking.credit;


import de.wps.ddd.banking.accountingevents.NewCustomerRegisteredEvent;

public class CustomerRegistrationEventHandler {

    private final CreditService creditService;

    public CustomerRegistrationEventHandler(CreditService creditService) {
        this.creditService = creditService;
    }

    void handle(NewCustomerRegisteredEvent newCustomer) {
        CustomerNumber customerNumber = new CustomerNumber(newCustomer.customerNumber());
        creditService.newCustomer(newCustomer.firstName(), newCustomer.familyName(), newCustomer.dateOfBirth(), customerNumber);
    }
}
