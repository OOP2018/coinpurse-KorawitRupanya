package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {
	
	
	@Override
	public Valuable createMoney(double value) {
		if(coin(value)) {
			return new Coin(value,"Baht");
			}
		if(bank(value)) {
			return new BankNote(value,"Baht");
		}
		return null;
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
}
