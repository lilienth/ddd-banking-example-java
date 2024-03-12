package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.ensureNotNull;
import static de.wps.common.contracts.BaseContracts.require;
import static de.wps.common.contracts.BaseContracts.requireNotNull;
import static de.wps.common.contracts.StringContracts.requireHasText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CreditCustomer {
    private final CustomerNumber customerNumber;
    private final String firstName;
    private final String familyName;
    private final LocalDate dateOfBirth;
    private final List<CreditAccount> accountList;
    private final List<Credit> creditList;

    public CreditCustomer(CustomerNumber customerNumber, String firstName, String familyName, LocalDate dateOfBirth) {
        requireNotNull(customerNumber, "customerNumber");
        requireHasText(firstName, "firstName");
        requireHasText(familyName, "familyName");
        requireNotNull(dateOfBirth, "dateOfBirth");

        this.firstName = firstName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.customerNumber = customerNumber;
        accountList = new ArrayList<>();
        creditList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public CustomerNumber getCustomerNumber() {
        return customerNumber;
    }

    public CreditAccount getAccount(CreditAccountNumber creditAccountNumber) {
        requireNotNull(creditAccountNumber, "accountNumber");
        require(hasAccount(creditAccountNumber), "hasAccount(accountNumber)");

        CreditAccount account = accountList.stream()
                .filter(a -> a.getAccountNumber().equals(creditAccountNumber))
                .findAny().orElse(null);

        ensureNotNull(account, "account");
        return account;
    }

    public void addAccount(CreditAccount account) {
        requireNotNull(account, "account");
        accountList.add(account);
    }

    public boolean hasAccount(CreditAccountNumber creditAccountNumber) {
        requireNotNull(creditAccountNumber, "accountNumber");
        return accountList.stream().anyMatch(a -> a.getAccountNumber().equals(creditAccountNumber));
    }

    public List<Credit> getCreditList() {
        return List.copyOf(creditList);
    }

    public void addCredit(Credit credit) {
        requireNotNull(credit, "credit");
        creditList.add(credit);
    }
}
