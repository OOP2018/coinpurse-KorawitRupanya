package coinpurse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tester class for check the method in the coin class.
 * 
 * @author Korawit Rupanya
 *
 */
public class MoneyUtil {

	public static void main(String[] args) {
		List<Valuable> valuables = new ArrayList<Valuable>();
		valuables.add(new Coin(10.0, "Bath"));
		valuables.add(new Coin(5.0, "Bath"));
		valuables.add(new Coin(2.0, "Bath"));
		valuables.add(new Coin(1.0, "Bath"));
		valuables.add(new Coin(1.0, "Yen"));
		valuables.add(new Coin(1.0, "Rupie"));
		valuables.add(new Coin(0.5, "Bath"));
		valuables.add(new Coin(-0.5, "Bath"));
		System.out.println("List of coins:");
		printValuable(valuables);
		System.out.println("\nSort coins:");
		sortCoins(valuables);
		printValuable(valuables);
		
		System.out.println("\nTest filter:");
		List<Valuable> testfilter = filterByCurrency(valuables,"Bath");
		printValuable( testfilter);
	}

	/**
	 * Print list of the coins.
	 * 
	 * @param coins
	 *            is the list of coins.
	 */
	public static void printValuable(List<Valuable> valuables) {
		for (int i = 0; i < valuables.size(); i++) {
			System.out.println(valuables.get(i));
		}
	}

	/**
	 * Sort the coins by their value.
	 * 
	 * @param coins
	 *            is the list of the coin.
	 */
	public static void sortCoins(List<Valuable> valuables) {
		Comparator<Valuable> comp = new ValueComparator();
		java.util.Collections.sort(valuables,comp);
	}

	/**
	 * Filter the coin by use their currency.
	 * 
	 * @param coins
	 *            is the list of coin with different value.
	 * @param currency
	 *            is the currency that used
	 * @return list of the coin with the same currency.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> valuables, String currency) {
		List<Valuable> filter = new ArrayList<Valuable>();
		for (Valuable v : valuables) {
			if (v.getCurrency().equals(currency)) {
				filter.add(v);
			}
		}
		return filter;
	}
}