package de.wps.ddd.banking.accountingevents;

import java.time.LocalDate;

public record NewCustomerRegisteredEvent(
        int customerNumber,
        String firstName,
        String familyName,
        LocalDate dateOfBirth
) {
}
