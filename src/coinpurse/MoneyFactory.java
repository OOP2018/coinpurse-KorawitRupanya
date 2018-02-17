package coinpurse;

/**
 * MoneyFactory class for creating money.
 * @author Korawit Rupanya
 *
 */

public abstract class MoneyFactory {

	/** */
	private static MoneyFactory instance = null;
	
	/** */
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
	 * create new money object in the local currency.
	 * If the value is not a valid currency amount,then throw IllegalArgumentException.
	 * @param value 
	 * @return
	 */
	public abstract Valuable createMoney(double value) ;
		
	public abstract String getCurrency();
	
	/**
	 * create new money object in the local currency.
	 * If the value is not a valid currency amount,then throw IllegalArgumentException.
	 * @param value
	 * @return
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
	
	public static void setFactory(MoneyFactory f) {
		instance = f;
	}
}
