package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable> {
	/**
     * Compare two objects that implement Valuable.
     * First compare them by currency, so that "Baht" < "Dollar".
     * If both objects have the same currency, order them by value.
     * @return boolean compare 
     */
	
	public int compare(Valuable x,Valuable y) {
		if(x.getCurrency().equals(y.getCurrency())) {
			if(x.getValue()>y.getValue()) {
				return 1;
			}
			else if(x.getValue()<y.getValue()) {
				return -1;
			}
			else return 0;
		}
		else return 0;
	}
}