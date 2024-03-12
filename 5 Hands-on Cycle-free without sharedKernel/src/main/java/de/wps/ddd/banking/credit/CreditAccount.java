package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.requireNotNull;


public class CreditAccount {
    private final CreditAccountNumber creditAccountNumber;
    private Amount balance;


    public CreditAccount(CreditAccountNumber creditAccountNumber, Amount amountOfCredit) {
        requireNotNull(creditAccountNumber, "accountNumber");
        requireNotNull(amountOfCredit, "amountOfCredit");

        this.creditAccountNumber = creditAccountNumber;
        this.balance = Amount.of(0).subtract(amountOfCredit);
    }

    public Amount getBalance() {
        return balance;
    }

    public CreditAccountNumber getAccountNumber() {
        return creditAccountNumber;
    }

    public void deposit(Amount amount) {
        requireNotNull(amount, "amount");
        this.balance = balance.add(amount);
    }

    public void withdraw(Amount amount) {
        requireNotNull(amount, "amount");
        this.balance = balance.subtract(amount);
    }
}
