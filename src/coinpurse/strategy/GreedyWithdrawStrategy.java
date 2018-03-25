package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * The withdraw strategy that will advice the withdraw method in the purse class what
 * to with draw to the user but not change anything to the list .
 * @author Korawit Rupanya
 *
 */

public class GreedyWithdrawStrategy implements WithdrawStrategy {

	
	/**
	 * Find and return items form a collection whose total value equals
	 * the requested amount.
	 * @param amount is the amount of money to withdraw,with currency.
	 * @param money the contents that are available for possible withdraw.
	 * 		  Must not be null,but may be an empty list.
	 * 		  This list is not modified.
	 * @return if a solution is found, return a list containing references
	 * 		   from the money parameter (List) whose sum equals the amount.
	 * 		   If a solution is not found, return empty list
	 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		List<Valuable> temporarylist = new ArrayList<Valuable>();
		Comparator<Valuable> comparator = new ValueComparator();
		if(amount == null || amount.getValue() == 0) return temporarylist;
		if( amount.getValue() < 0|| amount.getValue() < 0 || money.size() == 0)
			return null;
		money.sort(comparator);
		Collections.reverse(money);
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
