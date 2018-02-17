package coinpurse;

/**
 * Insert a Money by the currency and value that cannot be changed after the
 * money is created.The Money can be compared to other money.
 * 
 * @author Korawit Rupanya
 *
 */
public class Coin extends Money {
	/***
	 * Get the value as it not to be negative.
	 * 
	 * @param value
	 *            is value of the money.
	 * @param currency
	 *            is the currency of the money.
	 */
	public Coin(double value, String currency) {
		super(value,currency);
	}

	/**
	 * Format the value and the currency of the money into the string from.
	 * 
	 * @return value and currency of the money.
	 */
	@Override
	public String toString() {
		if (currency.equals("Ringgit")) {
			return String.format("%.2f-%s", value*100, "Sen");
		} else
			return String.format("%.2f-%s", value, currency);
	}
}