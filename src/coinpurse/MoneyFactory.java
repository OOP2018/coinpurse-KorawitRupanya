package coinpurse;

/**
 * MoneyFactory class for creating money.
 * @author Korawit Rupanya
 *
 */

public abstract class MoneyFactory {

	/**initial the instance of MoneyFactory*/
	private static MoneyFactory instance = null;
	
	protected MoneyFactory() {
	}
	
	/**
	 * Get an instance of MoneyFactory.
	 * @return an object of a subclass.
	 */
	public static MoneyFactory getInstance(){
		if (instance == null) {
			instance = new ThaiMoneyFactory();
		}
		return instance;
	}
	
	/**
	 * Create new money object in the local currency.
	 * If the value is not a valid currency amount,then throw IllegalArgumentException.
	 * @param value is amount of money to create.
	 * @return valuable that can be coins or money.
	 */
	public abstract Valuable createMoney(double value) ;
	
	/**
	 * Get the currency of a subclass
	 * @return currency of a subclass
	 */
	public abstract String getCurrency();
	
	/**
	 * Accepts money value as a String,
	 * Throws: IllegalArgumentException if value of string is not a number.
	 * @param value is amount of money to create.
	 * @return valuable that can be coins or money.
	 */
	public Valuable createMoney(String value) {
		double moneyValue = 0;
	    try {
	       moneyValue = Double.parseDouble(value);
	    } catch (Exception ex) {
	    		System.out.printf("The input %s is not a valid currency value%n",value); 
	    		throw new IllegalArgumentException();
	    }
	    return createMoney(moneyValue);
	}
	
	/**
	 * Static method to a "set" the MoneyFactory object that is used.
	 * This is mostly for testing of MoneyFactory.
	 * @param f is instance of MoneyFactory to choose what Factory.
	 */
	public static void setFactory(MoneyFactory f) {
		instance = f;
	}
}