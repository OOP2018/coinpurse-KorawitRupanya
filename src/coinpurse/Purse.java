package coinpurse;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;;

/**
 * A coin purse contains coins. You can insert coins, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Korawit Rupanya
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create the instance of comparator once.
	 */
	private static Comparator<Valuable> comparator = new ValueComparator();
	
	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of money you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		this.money = new ArrayList<Valuable>(capacity);
	}

	/**
	 * Count and return the number of money in the purse. This is the number of
	 * money, not their value.
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
		for (Valuable m : money) {
			balance += m.getValue();
		}
		return balance;
	}

	/**
	 * Return the capacity of the purse.
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
	 * Insert money into the purse. The Money is only inserted if the purse has
	 * space for it and the money has positive value. No worthless moneys!
	 * 
	 * @param Valuable
	 *            is a Valuable object to insert into purse
	 * @return true if Valuable inserted, false if can't insert
	 */
	public boolean insert(Valuable moneyInsert) {
		if (isFull() || moneyInsert.getValue() <= 0)
			return false;
		money.add(moneyInsert);
		return true;
	}

	/**
	 * Withdraw the requested amount of money that have same currency as the parameter.
	 * Return an array of Valuables withdrawn from purse, or return null if cannot
	 * withdraw the amount requested.
	 * @param amount is the amount to withdraw
	 * @return array of Valuable objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(Valuable amount) {
			money.sort(comparator);
			Collections.reverse(money);

			List<Valuable> temporarylist = new ArrayList<Valuable>();
			double amountNeededToWithdraw = amount.getValue();
			
			if(amount == null || amount.getValue() < 0||amountNeededToWithdraw < 0 || money.size() == 0
					|| this.getBalance() < amountNeededToWithdraw)
				return null;
			
			for (Valuable v : money) {
				if(amount.getCurrency().equals(v.getCurrency())) {
				if (amountNeededToWithdraw >= v.getValue()) {
					amountNeededToWithdraw -= v.getValue();
					temporarylist.add(v);
				}}
				if (amountNeededToWithdraw == 0)
					break;
			}
			if (amountNeededToWithdraw != 0) {
				return null;
			}
			
			for (Valuable value : temporarylist) {
				money.remove(value);
			}
			Valuable[] getArray = new Valuable[temporarylist.size()];
			return temporarylist.toArray(getArray);
		}
	
	/**
	 * Withdraw the requested amount of money with default currency "Baht". 
	 * Return an array of Valuables withdrawn
	 * from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Valuable objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(double amount) {
		return withdraw(new Money(amount,"Baht"));
	}

	/**
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {
		return String.format("You have %d valuable(s) with related to the value of %.2f", this.count(), this.getBalance());
	}
}