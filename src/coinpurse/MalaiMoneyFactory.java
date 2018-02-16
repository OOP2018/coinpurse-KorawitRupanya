package coinpurse;

public class MalaiMoneyFactory extends MoneyFactory {
	
	@Override
	public Valuable createMoney(double value) {
		if(coin(value)) {
			return new Coin (value*100,"Sen");
		}
		if(bank(value)) {
			return new BankNote(value,"Ringgit");
		}
		return null;
	}

	public boolean coin(double value) {
		double [] coins = {0.05,0.10,0.20,0.50};
		for(int i=0;i<coins.length;i++) {
			if(value == coins[i])return true;
		}
		return false;
	}
	
	public boolean bank(double value) {
		double [] banks = {1,2,5,10,20,50,100};
		for(int i=0;i<banks.length;i++) {
			if(value == banks[i])return true;
		}
		return false;
	}
}
