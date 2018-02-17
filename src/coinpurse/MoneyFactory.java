package coinpurse;

public abstract class MoneyFactory {

	private static MoneyFactory instance = null;
	
	protected MoneyFactory() {
	}
	
	public static MoneyFactory getInstance(){
		if (instance == null) {
			instance = new ThaiMoneyFactory();
		}
		return instance;
	}
	
	public abstract Valuable createMoney(double value) ;
		
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
