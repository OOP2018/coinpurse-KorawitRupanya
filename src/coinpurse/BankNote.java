package coinpurse;

/**
 * Insert a BankNote by the currency and value that cannot be changed after the
 * BankNote is created.The BankNote can be compared to other money.
 * 
 * @author Korawit Rupanya
 *
 */

public class BankNote implements Valuable {
	private long nextSerialNumber = 1000000;
	private double value;
	private String currency;
	private long serialNumber;

	/***
	 * Get the value and the currency.
	 * 
	 * @param value
	 *            is value of the money.
	 * @param currency
	 *            is the currency of the money.
	 */
	public BankNote(double value, String currency) {
		this.value = value;
		this.currency = currency;
		this.serialNumber = nextSerialNumber++;
	}

	/**
	 * Get the value of the money.
	 * 
	 * @return value of the money.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get the currency of the money.
	 * 
	 * @return currency of the money.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Get the serialNumber of any BankNote.
	 * 
	 * @return serialNumber of the bank note.
	 */
	public long getSerialnumber() {
		return serialNumber;
	}

	/**
	 * Compares the two object
	 * 
	 * @param arg
	 *            which is used to compare with another object.
	 * @return true if the object is the same , false is the object is null or if
	 *         the object is not the same.
	 */
	public boolean equals(Object arg) {
		if (this == arg)
			return true;
		if (arg == null)
			return false;
		if (this.getClass() != arg.getClass())
			return false;
		BankNote other = (BankNote) arg;
		return other.getValue() == this.getValue() && other.getCurrency().equals(this.getCurrency());
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