package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import coinpurse.Valuable;
import coinpurse.ValueComparator;

public class GreedyWithdrawStrategy implements WithdrawStrategy {

	private static Comparator<Valuable> comparator = new ValueComparator();
	
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		List<Valuable> temporarylist = new ArrayList<Valuable>();
		if(amount == null || amount.getValue() == 0) return temporarylist;
		if( amount.getValue() < 0|| amount.getValue() < 0 || money.size() == 0)
			return null;
		money.sort(comparator);
		double amountNeededToWithdraw = amount.getValue();
		
		for (Valuable v : money) {
			if(amount.getCurrency().equals(v.getCurrency())) {
			if (amountNeededToWithdraw >= v.getValue()) {
				amountNeededToWithdraw -= v.getValue();
				temporarylist.add(v);
			}}
			if (amountNeededToWithdraw == 0)
				return temporarylist;
		}
		return null;
	}
}
