package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

public class RecursiveWithdrawStrategy implements WithdrawStrategy {

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