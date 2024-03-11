package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.require;
import static de.wps.common.contracts.BaseContracts.requireNotNull;

import de.wps.ddd.banking.sharedKernel.Amount;
import de.wps.ddd.banking.sharedKernel.CreditNumber;
import java.util.Optional;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Credit {
    @Identity
    private final CreditNumber creditNumber;
    private final Amount amountOfCredit;
    private final CreditCustomer customer;
    private CreditAccount account;
    private Status status;

    public enum Status {
        applied, refused, granted, delayed, payed
    }

    public Credit(CreditNumber creditNumber, CreditCustomer customer, Amount amountOfCredit) {
        requireNotNull(creditNumber, "creditNumber");
        requireNotNull(customer, "customer");
        requireNotNull(amountOfCredit, "amountOfCredit");
        this.customer = customer;
        this.amountOfCredit = amountOfCredit;
        this.creditNumber = creditNumber;
        this.status = Status.applied;
    }

    public Amount getAmountOfCredit() {
        return amountOfCredit;
    }

    public CreditCustomer getCustomer() {
        return customer;
    }

    public CreditNumber getCreditNumber() {
        return creditNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void grant(CreditAccount account) {
        requireNotNull(account, "account");
        require(canBeGranted(), "canBeGranted()");

        this.status = Status.granted;
        this.account = account;
    }

    public boolean canBeGranted() {
        return (this.status != Status.refused && this.status != Status.granted);
    }

    public Optional<CreditAccount> getAccount() {
        return Optional.ofNullable(account);
    }

}
