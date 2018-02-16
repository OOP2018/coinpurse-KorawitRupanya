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
	       moneyValue = Double.parseDouble( value );
	    } catch (IllegalArgumentException ex) {
	    		System.out.println("The input doesn't contain number");
	    }
	    return createMoney(moneyValue);
	}
	
	public static void setFactory(MoneyFactory f) {
		instance = f;
	}
}
