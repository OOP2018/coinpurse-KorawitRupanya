package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * Tester class for check the method in the coin class.
 * @author Korawit Rupanya
 *
 */
public class MoneyUtil {
	
	public static void main(String[]args) {
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(new Coin(10.0,"Bath"));
		coins.add(new Coin(5.0,"Bath"));
		coins.add(new Coin(2.0,"Bath"));
		coins.add(new Coin(1.0,"Bath"));
		coins.add(new Coin(1.0,"Yen")); 
		coins.add(new Coin(1.0,"Rupie"));
		coins.add(new Coin(0.5,"Bath"));
		coins.add(new Coin(-0.5,"Yen"));
		printCoins(coins);
		System.out.println();
		sortCoins(coins);
		printCoins(coins);
	}
	public static void printCoins (List<Coin> coins) {
		for (int i =0;i<coins.size();i++) {
			System.out.println(coins.get(i));
		}
	}
	
	public static void sortCoins(List<Coin> coins) {
		java.util.Collections.sort(coins);
	}
}
