package coinpurse;

import java.util.Arrays;

public class TestForLab3 {
	
	public static void main(String[]args) {
		Purse p = new Purse(25);
		System.out.println(p.insert(new Coin(20,"Baht")));
		System.out.println(Arrays.toString(p.withdraw(20)));
		System.out.println(p.getBalance());
		Purse q = new Purse(50);
		System.out.println(q.insert(new BankNote(35,"Dollar",1000000)));
		System.out.println(Arrays.toString(q.withdraw(20)));
	}

}
