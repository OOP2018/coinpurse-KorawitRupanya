package coinpurse;

/**
 * Insert a Money by the currency and value that cannot be changed after the
 * money is created.The Money can be compared to other money.
 * 
 * @author Korawit Rupanya
 *
 */
public class Coin implements Comparable<Coin>, Valuable {
	private double value;
	private String currency;

	/***
	 * Get the value as it not to be negative.
	 * 
	 * @param value
	 *            is value of the money.
	 * @param currency
	 *            is the currency of the money.
	 */
	public Coin(double value, String currency) {
		if (value > 0)
			this.value = value;
		this.currency = currency;
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
		Coin other = (Coin) arg;
		return other.getValue() == this.getValue() && other.getCurrency().equals(this.getCurrency());
	}

	/**
	 * Compares the value of two object by getValue
	 * 
	 * @param coin
	 *            which is used to compared with value with another value.
	 * @return 1 if the money is over another value -1 if the money is less than
	 *         another value. 0 if the money is equal to another value.
	 */
	@Override
	public int compareTo(Coin coin) {
		if (this.getValue() > coin.getValue())
			return 1;
		if (this.getValue() < coin.getValue())
			return -1;
		if (this.getValue() == coin.getValue())
			return 0;
		return 0;
	}

	/**
	 * Format the value and the currency of the money into the string from.
	 * 
	 * @return value and currency of the money.
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s", value, currency);

	}
}