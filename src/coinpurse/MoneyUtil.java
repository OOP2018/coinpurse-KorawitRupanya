package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * Tester class for check the method in the coin class.
 * 
 * @author Korawit Rupanya
 *
 */
public class MoneyUtil {

	public static void main(String[] args) {
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(new Coin(10.0, "Bath"));
		coins.add(new Coin(5.0, "Bath"));
		coins.add(new Coin(2.0, "Bath"));
		coins.add(new Coin(1.0, "Bath"));
		coins.add(new Coin(1.0, "Yen"));
		coins.add(new Coin(1.0, "Rupie"));
		coins.add(new Coin(0.5, "Bath"));
		coins.add(new Coin(-0.5, "Bath"));
		printCoins(coins);
		System.out.println();
		sortCoins(coins);
		printCoins(coins);
	}

	/**
	 * Print list of the coins.
	 * 
	 * @param coins
	 *            is the list of coins.
	 */
	public static void printCoins(List<Coin> coins) {
		for (int i = 0; i < coins.size(); i++) {
			System.out.println(coins.get(i));
		}
	}

	/**
	 * 
	 * @param coins
	 */
	public static void sortCoins(List<Coin> coins) {
		java.util.Collections.sort(coins);
	}

/**
 * 
 * @param coins
 * @param currency
 * @return
 */
	public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {
		List<Coin> filter = new ArrayList<Coin>();
		for (Coin coin : coins) {
			if (coin.getCurrency().equals(currency)) {
				filter.add(coin);
			}
		}
		return filter;
	}
}
