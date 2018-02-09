package coinpurse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse using JUnit. This is a JUnit 4 test suite.
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4, but you have to
 * tell the IDE to add it to your project as a "Library". To run these tests,
 * right click on this file (in Project panel) and choose Run As -> JUnit test
 * 
 * @author Resident Evil
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BTC";

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}

	/** Make a coin with the default currency. To save typing "new Coin(...)" */
	private Valuable makeCoin(double value) {
		return new Coin(value, CURRENCY);
	}

	/** Easy test that the Purse constructor is working. */
	@Test
	public void testConstructor() {
		Purse purse = new Purse(3);
		assertEquals(3, purse.getCapacity());
		assertEquals(false, purse.isFull());
		assertEquals(0, purse.count());
	}

	/** Insert some coins. Easy test. */
	@Test
	public void testInsert() {
		Purse purse = new Purse(3);
		Valuable val1 = makeCoin(5);
		Valuable val2 = makeCoin(10);
		Valuable val3 = makeCoin(1);
		assertTrue(purse.insert(val1));
		assertTrue(purse.insert(val3));
		assertTrue(purse.insert(val2));
		assertEquals(3, purse.count());
		// purse is full so insert should fail
		assertFalse(purse.insert(makeCoin(1)));
	}

	/** Insert should reject coin with no value. */
	@Test
	public void testInsertNoValue() {
		Purse purse = new Purse(3);
		Valuable fakeCoin = new BankNote(0, CURRENCY);
		assertFalse(purse.insert(fakeCoin));
	}

	@Test(timeout = 1000)
	public void testIsFull() { // borderline case (capacity 1)
		Purse purse = new Purse(1);
		assertFalse(purse.isFull());
		purse.insert(makeCoin(1));
		assertTrue(purse.isFull());
		// real test
		int capacity = 4;
		purse = new Purse(capacity);
		for (int k = 1; k <= capacity; k++) {
			assertFalse(purse.isFull());
			purse.insert(makeCoin(k));
		}
		// should be full now
		assertTrue(purse.isFull());
		assertFalse(purse.insert(makeCoin(5)));
	}

	/**
	 * Should be able to insert same coin many times, since spec doesn't say
	 * anything about this.
	 */
	@Test(timeout = 1000)
	public void testInsertSameCoin() {
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		Valuable v = new BankNote(value, "THB");
		assertTrue(purse.insert(v));
		assertTrue(purse.insert(v)); // should be allowed
		assertTrue(purse.insert(v)); // should be allowed
		assertTrue(purse.insert(v)); // should be allowed
		assertTrue(purse.insert(v)); // should be allowed
		assertEquals(purse.getBalance(), 5 * value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout = 1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double[] values = { 1, 20, 0.5, 10 }; // values of coins we will insert

		for (double value : values) {
			Valuable v = makeCoin(value);
			assertTrue(purse.insert(v));
			assertEquals(value, purse.getBalance(), TOL);
			Valuable[] result = purse.withdraw(value);
			assertTrue(result != null);
			assertEquals(1, result.length);
			assertSame(v, result[0]); // should be same object
			assertEquals(0, purse.getBalance(), TOL);
		}
	}

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout = 1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Valuable[] v = { makeCoin(5.0), makeCoin(10.0), makeCoin(1.0), makeCoin(5.0) };
		// insert them all
		for (Valuable coin : v)
			assertTrue(purse.insert(coin));

		double amount1 = v[1].getValue() + v[3].getValue();
		double amount2 = v[0].getValue() + v[2].getValue();
		assertEquals(amount1 + amount2, purse.getBalance(), TOL);

		Valuable[] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL);

		assertEquals(amount2, purse.getBalance(), TOL);
		Valuable[] wd2 = purse.withdraw(amount2);

		// should be empty now
		assertEquals(0, purse.getBalance(), TOL);
	}

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout = 1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Valuable> v = Arrays.asList(makeCoin(1.0), makeCoin(0.5), makeCoin(10.0), makeCoin(0.25), makeCoin(5.0));
		// num = number of coins to insert and then withdraw
		for (int num = 1; num <= v.size(); num++) {
			double amount = 0.0;
			List<Valuable> subList = v.subList(0, num);
			for (Valuable v1 : subList) {
				purse.insert(v1);
				amount += v1.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals(amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s", amount,
					Arrays.toString(subList.toArray()));
			assertNotNull(errmsg, result);
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals(0.0, purse.getBalance(), TOL);
		}
	}

	@Test(timeout = 1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull(purse.withdraw(1));
		purse.insert(makeCoin(20));
		assertNull(purse.withdraw(1));
		assertNull(purse.withdraw(19));
		assertNull(purse.withdraw(21));
		purse.insert(makeCoin(20)); // now it has 20 + 20
		assertNull(purse.withdraw(30));
	}
	
	/**
	 * Test withdraw method
	 */
	@Test(timeout = 1000)
	public void testWithdraw() {
		Purse purse = new Purse(10);
		Money twenty = new BankNote(20,"Baht");
		Money fifthy = new BankNote(50,"Yen");
		Money onehundred = new BankNote(100,"Dollars");
		purse.insert(twenty);
		purse.insert(fifthy);
		purse.insert(onehundred);
		assertEquals(170,purse.getBalance(),TOL);
		
	}
	
	/**
	 * Test equals method.
	 */
	@Test(timeout = 1000)
	public void testEquals() {
		Valuable c = new Coin(10,"Baht");
		Valuable d = new Coin(5,"Baht");
		Valuable e = new Coin(10,"Baht");
		Valuable f = new Coin(10,"Yen");
		Valuable g = new BankNote(100,"Dollar");
		Valuable h = new BankNote(100,"Dollar");
		Valuable i = new BankNote(50,"Yuan");
		Valuable j = new BankNote(20,"Dollar");
		assertFalse(c.equals(d));
		assertTrue(c.equals(e));
		assertFalse(c.equals(f));
		assertTrue(g.equals(h));
		assertFalse(g.equals(i));
		assertFalse(g.equals(j));
	}

	/**
	 * Sum the value of some coins.
	 * 
	 * @param wd1
	 *            array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] wd1) {
		if (wd1 == null)
			return 0.0;
		double sum = 0;
		for (Valuable c : wd1)
			if (c != null)
				sum += c.getValue();
		return sum;
	}
}