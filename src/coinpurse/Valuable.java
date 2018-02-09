package coinpurse;
/**
 * An interface of the objects having a monetary value and currency.
 * @author Korawit Rupanya
 */
public interface Valuable extends Comparable<Valuable> {

	/**
	 * Get the value of the objects with their currency.
	 * @return the value of this object.
	 */
	public double getValue();

	/**
	 * Get the currency of the object.
	 * @return the currency of this object.
	 */
	public String getCurrency();

}
