package coinpurse.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.Money;
import coinpurse.Valuable;

/**
 * The test of withdraw by JUnit 4 test.
 * @author Korawit Rupanya
 *
 */
public class WithdrawTest  {
	private List<Valuable>money;
	private GreedyWithdrawStrategy greed;
//	private RecursiveWithdrawStrategy greed;
	@Before
	public void setUp () throws Exception {
		money = new ArrayList<>();
		greed = new GreedyWithdrawStrategy();
//		greed = new RecursiveWithdrawStrategy();
	}
	
	/**Check for the simple withdraw test **/
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
		
		/**check if the size remain the same after the strategy do it job **/
		assertEquals(listSize,solution.size());
		/**check if the size of money equal to the actual money size **/
		assertEquals(listSize, money.size());
	}
	
	/**Check for the impossible withdraw case that if can the value of money 
	  which the user want to withdraw is more that in the purse so return null**/
	@Test(timeout=100)
	public void testImpossibleWithdraw() {
		money.add(new Money(1,"Baht"));//Only have 1 baht
		money.add(new Money(1,"Ringgit"));
		Valuable v = new Money(100, "Baht");//Want to with draw 100 baht
		Valuable v1 = new Money(100,"Ringgit");

		assertNull(greed.withdraw(v, money));//Must be null
		
		assertNull(greed.withdraw(v1, money));
	}
	
	/**Check if there is many possible way to with draw but when user with draw 
	 the value of the money that give to the user must biggest value of money related to 
	 what the money in the purse*/
	@Test(timeout=100)
	public void testPossibleWithdrawCase() {
		
		money.add(new Money(1,"Baht"));//Add 1 baht five times
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(1,"Baht"));
		money.add(new Money(5,"Baht"));//Add 5 baht
		
		money.add(new Money(50,"Ringgit"));//Add 50 ringgit 2 times
		money.add(new Money(50,"Ringgit"));
		money.add(new Money(100,"Ringgit"));//Add 100 ringgit
		
		Valuable v = new Money(5,"Baht");
		
		List<Valuable> solution = greed.withdraw(v, money);
		assertEquals(1,solution.size());
		assertEquals(new Money(5, "Baht"), solution.get(0));//There must be five baht for the solution
		
		Valuable v1 = new Money(100,"Ringgit");
		List<Valuable>sol = greed.withdraw(v1, money);
		assertEquals(new Money(100, "Ringgit"), sol.get(0));//There must be 100 ringgit for the solution
		assertEquals(1, sol.size());
	}
	
	/**Test if the size of money be equals to zero when not thing inside**/
	@Test(timeout=100)
	public void testWithdrawEmptymoney(){
		int listSize = money.size();
		
		assertEquals(0, listSize);
		
		Valuable v = new Money(5, "Baht");
		assertNull(greed.withdraw(v, money));//Must be null cause not thing added yet
		
		Valuable v1 = new Money(5, "Ringgit");
		assertNull(greed.withdraw(v1, money));//Must be null cause not thing added yet
		
	}
	
	/**Test for that empty value to withdraw */
	@Test(timeout=100)
	public void testWithdrawEmtyValue(){
		money.add(new Money(1,"Baht"));
		assertEquals(0, greed.withdraw(null, money).size());
		money.add(new Money(1, "Ringgit"));
		assertEquals(0, greed.withdraw(new Money(0, "Ringgit"), money).size());
	}
	
	/**Test for withdraw different currency*/
	@Test(timeout=100)
	public void testWithdrawDifferentCurrency() {
		money.add(new Money(1.0, "Ringgit"));
		money.add(new Money(1.0, "Yen"));
		money.add(new Money(1.0, "Dollar"));
		money.add(new Money(1.0, "Baht"));
		assertEquals(new Money(1.0, "Baht"),greed.withdraw(new Money(1.0, "Baht"), money).get(0));
	}
}
