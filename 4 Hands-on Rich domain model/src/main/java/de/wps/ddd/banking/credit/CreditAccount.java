package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.requireNotNull;

import de.wps.ddd.banking.sharedKernel.AccountNumber;
import de.wps.ddd.banking.sharedKernel.Amount;
import org.jmolecules.ddd.annotation.Entity;

@Entity
public class CreditAccount {
    private final AccountNumber accountNumber;
    private final CreditCustomer accountOwner;
    private final Credit credit;
    private Amount balance;

    public CreditAccount(AccountNumber accountNumber, Credit credit) {
        requireNotNull(accountNumber, "accountNumber");
        requireNotNull(credit, "credit");
        this.credit = credit;
        this.balance = Amount.of(0).subtract(credit.getAmountOfCredit());
        this.accountNumber = accountNumber;
        this.accountOwner = credit.getCustomer();
    }


    public Amount getBalance() {
        return balance;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void deposit(Amount amount) {
        requireNotNull(amount, "amount");
        this.balance = balance.add(amount);
    }

    public void withdraw(Amount amount) {
        requireNotNull(amount, "amount");
        this.balance = balance.subtract(amount);
    }

    public CreditCustomer getAccountOwner() {
        return accountOwner;
    }

}
