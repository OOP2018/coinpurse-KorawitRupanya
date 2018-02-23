package coinpurse;

/**
 * MoneyFactory class for creating Thai money.
 * 
 * @author Korawit Rupanya
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {

	/** The serialNumber of Thai's money */
	private static long serialNumber = 1000000;
	/**
	 * Create new money object in Thai currency. If the value is not a valid
	 * currency amount,then throw IllegalArgumentException.
	 * 
	 * @param value
	 *            is amount of money to create.
	 * @return valuable that can be coins or money.
	 */
	@Override
	public Valuable createMoney(double value) {
		if (isCoin(value)) {
			return new Coin(value, "Baht");
		}
		if (isBank(value)) {
			return new BankNote(value, "Baht",serialNumber);
		} else
			throw new IllegalArgumentException("Sorry,Thailand doesn't have this value of banknote or coin.");
	}

	/**
	 * Check if an input value is valid to make a coin in Thailand.
	 * 
	 * @param value
	 *            is amount of value to create a coin.
	 * @return true if it's in the coin value of Thai currency,if it's not then
	 *         return false.
	 */
	private boolean isCoin(double value) {
		double[] coins = { 1, 2, 5, 10 };
		for (int i = 0; i < coins.length; i++) {
			if (value == coins[i])
				return true;
		}
		return false;
	}

	/**
	 * Check if an input value is valid to make a bank note in Thailand.
	 * 
	 * @param value
	 *            is amount of value to create a bank note.
	 * @return true if it's in the bank note value of Thai currency,if it's not then
	 *         return false.
	 */
	private boolean isBank(double value) {
		double[] banks = { 20, 50, 100, 500, 1000 };
		for (int i = 0; i < banks.length; i++) {
			if (value == banks[i])
				return true;
		}
		return false;
	}

	/**
	 * Get the currency
	 * 
	 * @return currency is "Baht"
	 */
	@Override
	public String getCurrency() {
		return "Baht";
	}
}
