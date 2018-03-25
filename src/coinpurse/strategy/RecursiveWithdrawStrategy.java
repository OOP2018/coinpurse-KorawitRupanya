package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * The withdraw strategy that will advice the withdraw method in the purse class what
 * to with draw to the user but not change anything to the list .
 * @author Korawit Rupanya
 *
 */
public class RecursiveWithdrawStrategy implements WithdrawStrategy {

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
		List<Valuable> tempList = new ArrayList<>();
		if (amount == null || amount.getValue() == 0 )
			return tempList;
		Comparator<Valuable> comparator = new ValueComparator();
		Collections.sort(money,comparator);
		Collections.reverse(money);
		if (helper(0, money, amount, tempList)) {	
			return tempList;
		}
		return null;
	}

	/**
	 * Helper class used to make the recursive.
	 * @param start
	 * @param money
	 * @param amount
	 * @param tempList
	 * @return
	 */
	public boolean helper(int start, List<Valuable> money, Valuable amount, List<Valuable> tempList) {
		if (start >= money.size())
			return amount.getValue() == 0;
		if (money.get(start).getCurrency().equals(amount.getCurrency())) {
			if (helper(start + 1, money,
					new Money(amount.getValue() - money.get(start).getValue(), money.get(start).getCurrency()),
					tempList)) {
				tempList.add(money.get(start));
				return true;
			}
		}
		if (helper(start + 1, money, amount, tempList)) {
			return true;
		}
		return false;
	}
}