package coinpurse.strategy;

import java.util.List;

import coinpurse.Valuable;

/**
 * The withdraw strategy that will advice the withdraw method in the purse class what
 * to with draw to the user but not change anything to the list .
 * @author Korawit Rupanya
 *
 */
public interface WithdrawStrategy {

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
	public List <Valuable> withdraw (Valuable amount,List<Valuable>money);
	
}
