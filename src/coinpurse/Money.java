package coinpurse;

public class Money implements Valuable {

	protected double value;
	protected String currency;

	public Money(double value,String currency) {
		if(value <= 0) this.value = 0.0;
		this.value=value;
		this.currency=currency;
	}

	/**
	 * Compare two object that implement Valuable
	 * order items by currency (so items with the same currency are grouped together)
	 * if two items  have the same currency then order by value.
	 * @param o that is the object that we compare with.
	 * @return boolean compare.
	 */
	public int compareTo(Valuable o) {
	if(this.getCurrency().equals(o.getCurrency())) {
		return Double.compare(this.getValue(), o.getValue());
	}
	return this.getCurrency().compareTo(o.getCurrency());
	}
	
	/**
	 * Get the value of the money.
	 * 
	 * @return value of the money.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get the currency of the money.
	 * 
	 * @return currency of the money.
	 */
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * Compares the two object
	 * 
	 * @param arg
	 *            which is used to compare with another object.
	 * @return true if the object is the same , false is the object is null or if
	 *         the object is not the same.
	 */
	public boolean equals(Object arg) {
		if (this == arg)
			return true;
		if (arg == null)
			return false;
		if (this.getClass() != arg.getClass())
			return false;
		Valuable other = (Valuable) arg;
		return other.getValue() == this.getValue() && other.getCurrency().equals(this.getCurrency());
	}

}