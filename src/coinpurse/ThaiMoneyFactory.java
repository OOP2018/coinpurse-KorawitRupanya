package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {
	
	
	@Override
	public Valuable createMoney(double value) {
		if(coin(value)) {
			return new Coin(value,"Baht");
			}
		else if(bank(value)) {
			return new BankNote(value,"Baht");
		}
		else throw new IllegalArgumentException("Sorry,Thailand doesn't have this value of banknote or coin.");
	}
	
	public boolean coin(double value) {
		double [] coins = {1,2,5,10};
		for(int i=0;i<coins.length;i++) {
			if(value == coins[i])return true;
		}
		return false;
	}
	
	public boolean bank(double value) {
		double [] banks = {20,50,100,500,1000};
		for(int i=0;i<banks.length;i++) {
			if(value == banks[i])return true;
		}
		return false;
	}

	@Override
	public String getCurrency() {
		return "Baht";
	}
}
