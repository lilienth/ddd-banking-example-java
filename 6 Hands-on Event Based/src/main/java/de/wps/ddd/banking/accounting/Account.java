package de.wps.ddd.banking.accounting;

import static de.wps.common.contracts.BaseContracts.require;
import static de.wps.common.contracts.BaseContracts.requireNotNull;

public class Account {
    private final AccountNumber accountNumber;
    private Amount balance;

    public Account(AccountNumber accountNumber) {
        requireNotNull(accountNumber, "accountNumber");

        this.accountNumber = accountNumber;
        this.balance = Amount.of(0);
    }

    public Amount getBalance() {
        return balance;
    }

    public void withdraw(Amount amount) {
        requireNotNull(amount, "amount");
        require(amount.isLessOrEquals(getBalance()), "amount.isLessOrEquals(getBalance())");

        this.balance = this.balance.subtract(amount);
    }

    public void deposit(Amount amount) {
        requireNotNull(amount, "amount");

        this.balance = this.balance.add(amount);
    }

    public AccountNumber getAccountnumber() {
        return accountNumber;
    }

}
