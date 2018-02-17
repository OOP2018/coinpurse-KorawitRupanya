package coinpurse;

/**
 * MoneyFactory class for creating Malay money.
 * 
 * @author Korawit Rupanya
 *
 */
public class MalayMoneyFactory extends MoneyFactory {

	/**
	 * Create new money object in Malay currency. If the value is not a valid
	 * currency amount,then throw IllegalArgumentException.
	 * 
	 * @param value
	 *            is amount of money to create.
	 * @return valuable that can be coins or money.
	 */
	@Override
	public Valuable createMoney(double value) {
		if (coin(value)) {
			return new Coin(value, "Ringgit");
		}
		if (bank(value)) {
			return new BankNote(value, "Ringgit");
		}
		throw new IllegalArgumentException("Sorry,Malay doesn't have this value of banknote or coin.");
	}

	/**
	 * Check if an input value is valid to make a coin in Malaysia.
	 * 
	 * @param value
	 *            is amount of value to create a coin.
	 * @return true if it's in the coin value of Malay currency,if it's not then
	 *         return false.
	 */
	public boolean coin(double value) {
		double[] coins = { 0.05, 0.10, 0.20, 0.50 };
		for (int i = 0; i < coins.length; i++) {
			if (value == coins[i])
				return true;
		}
		return false;
	}

	/**
	 * Check if an input value is valid to make a bank note in Malaysia.
	 * 
	 * @param value
	 *            is amount of value to create a bank note.
	 * @return true if it's in the bank note value of Malay currency,if it's not
	 *         then return false.
	 */
	public boolean bank(double value) {
		double[] banks = { 1, 2, 5, 10, 20, 50, 100 };
		for (int i = 0; i < banks.length; i++) {
			if (value == banks[i])
				return true;
		}
		return false;
	}

	/**
	 * Get the currency
	 * 
	 * @return currency is "Ringgit"
	 */
	@Override
	public String getCurrency() {
		return "Ringgit";
	}
}
