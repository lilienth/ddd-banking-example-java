package de.wps.ddd.banking.sharedKernel;

import static de.wps.common.contracts.BaseContracts.require;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 * ValueObject, representing a syntactically valid customer number
 *
 * <p>Implemented as a record with:</p>
 * <ul>
 *     <li>isValid method to check validity</li>
 *     <li>a public constructor directly coupled to the internal representation</li>
 *     <li>validation implemented in the compact constructor</li>
 *     <li>default method to access the internal representation</li>
 *     <li>equals/hashCode automatically based on the internal int value</li>
 * </ul>
 *
 * @param customerNumberValue internal value of the customer number
 * @see CreditNumber
 * @see AccountNumber
 */
@ValueObject
public record CustomerNumber(int customerNumberValue) {
    public CustomerNumber {
        require(isValid(customerNumberValue), "isValid(customerNumberValue)");
    }

    public static boolean isValid(int customerNumberValue) {
        return customerNumberValue > 0;
    }
}
