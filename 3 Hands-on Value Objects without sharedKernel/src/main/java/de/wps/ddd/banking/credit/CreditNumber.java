package de.wps.ddd.banking.credit;

import static de.wps.common.contracts.BaseContracts.require;

/**
 * ValueObject representing a syntactically valid credit numbers
 *
 * <p>Implemented as a record with:</p>
 * <ul>
 *     <li>isValid method to check for validity</li>
 *     <li>a factory method "of" to try to control object creation and decouple external and internal representation</li>
 *     <li>public default record constructor, which must not be used directly, see ArchUnit-Test</li>
 *     <li>equals/hashCode based on the internal int value</li>
 * </ul>
 * @see CustomerNumber for an alternative way of implementing value objects
 * @see AccountNumber for an alternative way of implementing value objects
 */
public record CreditNumber(int creditNumberValue) {

	public static boolean isValid(int creditNumberValue) {
		return creditNumberValue > 0;
	}
	public static CreditNumber of(int creditNumberValue) {
		require(isValid(creditNumberValue), "isValid(creditNumberValue)");
		return new CreditNumber(creditNumberValue);
	}

	public int value() {
		return creditNumberValue;
	}
}