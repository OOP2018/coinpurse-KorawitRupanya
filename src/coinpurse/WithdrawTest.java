package coinpurse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.strategy.GreedyWithdrawStrategy;

public class WithdrawTest  {
	private List<Valuable>money;
	private GreedyWithdrawStrategy greed;
	@Before
	public void setUp () throws Exception {
		money = new ArrayList<>();
		greed = new GreedyWithdrawStrategy();
	}
	
	@Test(timeout=100)
	public void testSimpleWithdrawStategy() {
		money.add(new Money(1, "Baht"));
		money.add(new Money(5, "Baht"));
		money.add(new Money(10, "Baht"));
		money.add(new Money(20, "Baht"));
		money.add(new Money(50,"Baht"));
		money.add(new Money(100, "Baht"));
		money.add(new Money(500, "Baht"));
		money.add(new Money(1000, "Baht"));
		int listSize = money.size();/**get the size for check the money**/
		System.out.println(listSize);
		Valuable v = new Money(1686, "Baht");
		List<Valuable>solution = greed.withdraw(v, money);
		
		/**check if the size remain the same after the geed do it job **/
		assertEquals(listSize,solution.size());
		System.out.println(money.size());
		/**check if the size of money equal to the actual money size **/
		assertEquals(listSize, money.size());
	}
	
	@Test(timeout=100)
	public void testImpossibleWithdraw() {
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Ringgit"));
		Valuable v = new Money(100, "Baht");
		Valuable v1 = new Money(100,"Ringgit");
		
		assertNull(greed.withdraw(v, money));
		
		assertNull(greed.withdraw(v1, money));
	}

	@Test(timeout=100)
	public void testPossibleWithdrawCase() {
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(5,"Baht"));
		
		money.add(new Money(50,"Ringgit"));
		money.add(new Money(50,"Ringgit"));
		money.add(new Money(100,"Ringgit"));
		
		Valuable v = new Money(5,"Baht");
		List<Valuable>solution = greed.withdraw(v, money);
		assertEquals(5, solution.size());
		
		Valuable v1 = new Money(100,"Ringgit");
		List<Valuable>sol = greed.withdraw(v1, money);
		assertEquals(2, sol.size());
	}
	
	@Test(timeout=100)
	public void testWithdrawEmptymoney(){
		int listSize = money.size();
		
		assertEquals(0, listSize);
		
		Valuable v = new Money(5, "Baht");
		assertNull(greed.withdraw(v, money));
		
		Valuable v1 = new Money(5, "Ringgit");
		assertNull(greed.withdraw(v1, money));
		
	}
	
	@Test(timeout=100)
	public void testWithdrawEmtyValue(){
		money.add(new Money(1,"Baht"));
		assertEquals(0, greed.withdraw(null, money).size());
		money.add(new Money(1, "Ringgit"));
		assertEquals(0, greed.withdraw(new Money(0, "Ringgit"), money).size());
	}
	
	@Test(timeout=100)
	public void testWithdrawEverythings() {
		
	}
}
