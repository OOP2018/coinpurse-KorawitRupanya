package coinpurse;

/**
 * Insert a BankNote by the currency and value that cannot be changed after the
 * BankNote is created.The BankNote can be compared to other money.
 * 
 * @author Korawit Rupanya
 *
 */

public class BankNote extends Money {
	/** The serial number of bank note*/
	private long serialNumber;

	/***
	 * Get the value and the currency.
	 * 
	 * @param value
	 *            is value of the money.
	 * @param currency
	 *            is the currency of the money.
	 */
	public BankNote(double value, String currency,long serialNumber) {
		super(value, currency);
		this.serialNumber=serialNumber;
	}
	/**
	 * Get the serialNumber of any BankNote.
	 * 
	 * @return serialNumber of the bank note.
	 */
	public long getSerial() {
		return this.serialNumber;
	}

	/**
	 * Format the value and the currency of the money into the string from.
	 * 
	 * @return value and currency of the money.
	 */
	public String toString() {
		return String.format("%.2f-%s note [%d]", this.value, this.currency, this.serialNumber);
	}
}