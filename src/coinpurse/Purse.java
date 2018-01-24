package coinpurse;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;;

/**
 * A coin purse contains coins. You can insert coins, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Korawit Rupanya
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Coin> money;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		this.money = new ArrayList<Coin>(capacity);
	}

	/**
	 * Count and return the number of coins in the purse. This is the number of
	 * coins, not their value.
	 * 
	 * @return the number of coins in the purse
	 */
	public int count() {
		return money.size();
	}

	/**
	 * Get the total value of all items in the purse.
	 * 
	 * @return the total value of items in the purse.
	 */
	public double getBalance() {
		double balance = 0;
		for (Coin coin : money) {
			balance += coin.getValue();
		}
		return balance;
	}

	/**
	 * Return the capacity of the coin purse.
	 * 
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in purse
	 * equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		return money.size() >= this.getCapacity();
	}

	/**
	 * Insert a coin into the purse. The coin is only inserted if the purse has
	 * space for it and the coin has positive value. No worthless coins!
	 * 
	 * @param coin
	 *            is a Coin object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Coin coin) {
		if (isFull() || coin.getValue() <= 0)
			return false;
		money.add(coin);
		return true;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins withdrawn
	 * from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Coin objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Coin[] withdraw(double amount) {
		if (amount <= 0 || getBalance() < amount)
			return null;

		List<Coin> temporarylist = new ArrayList<Coin>();
		Collections.sort(money);
		Collections.reverse(money);

		double amountNeededToWithdraw = amount;
		for (Coin coin : money) {
			if (amountNeededToWithdraw >= coin.getValue()) {
				amountNeededToWithdraw -= coin.getValue();
				temporarylist.add(coin);
			}
			if (amountNeededToWithdraw == 0)
				break;
		}
		if (amountNeededToWithdraw != 0) {
			return null;
		}

		for (Coin coin : temporarylist) {
			money.remove(coin);
		}
		Coin[] getArray = new Coin[temporarylist.size()];
		return temporarylist.toArray(getArray);
	}

	/**
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {
		return String.format("You have %d coins with related to the value of %.2f", this.count(), this.getBalance());
	}

}