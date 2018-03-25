package coinpurse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;

import coinpurse.strategy.GreedyWithdrawStrategy;
import coinpurse.strategy.RecursiveWithdrawStrategy;

public class WithdrawTest  {
	private List<Valuable>money;
	private RecursiveWithdrawStrategy greed;
	@Before
	public void setUp () throws Exception {
		money = new ArrayList<>();
		greed = new RecursiveWithdrawStrategy();
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
		Valuable v = new Money(1686, "Baht");
		List<Valuable>solution = greed.withdraw(v, money);
		
		/**check if the size remain the same after the geed do it job **/
		assertEquals(listSize,solution.size());
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
		
		List<Valuable> solution = greed.withdraw(v, money);
		System.out.println("na");
		System.out.println(solution==null);
		System.out.println(solution.size());
		for(int i=0;i<solution.size();i++){
		    System.out.println(solution.get(i));
		} 
		assertEquals(1,solution.size());
		assertEquals(new Money(5, "Baht"), solution.get(0));
		
		Valuable v1 = new Money(100,"Ringgit");
		List<Valuable>sol = greed.withdraw(v1, money);
		assertEquals(new Money(100, "Ringgit"), sol.get(0));
		assertEquals(1, sol.size());
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
	public void testWithdrawDifferentCurrency() {
		money.add(new Money(1.0, "Ringgit"));
		money.add(new Money(1.0, "Yen"));
		money.add(new Money(1.0, "Dollar"));
		money.add(new Money(1.0, "Baht"));
		assertEquals(new Money(1.0, "Baht"),greed.withdraw(new Money(1.0, "Baht"), money).get(0));
	}
}
