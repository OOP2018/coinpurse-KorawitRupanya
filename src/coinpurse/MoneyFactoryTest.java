package coinpurse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Money Factory test
 * 
 * @author Korawit Rupanya
 */

public class MoneyFactoryTest {
	/** Thai currency */
	private final String BAHT = "Baht";
	/** malay currency */
	private final String RINGGIT = "Ringgit";

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}

	@Test
	public void testGetInstance() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory m = MoneyFactory.getInstance();
		assertEquals("coinpurse.ThaiMoneyFactory", m.getClass().getName());
		assertEquals(ThaiMoneyFactory.getInstance(), m);
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory m1 = MoneyFactory.getInstance();
		assertEquals("coinpurse.MalayMoneyFactory", m1.getClass().getName());
		assertEquals(MalayMoneyFactory.getInstance(), m1);
	}

	/** test make Thai money */
	@Test
	public void testCreatThaiMoney() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory m = MoneyFactory.getInstance();
		assertEquals(new Coin(1, BAHT), m.createMoney(1));
		assertEquals(new Coin(2, BAHT), m.createMoney("2"));
		assertEquals(new Coin(5, BAHT), m.createMoney(5));
		assertEquals(new Coin(10, BAHT), m.createMoney("10"));
		assertEquals(new BankNote(20, BAHT,1000000), m.createMoney(20));
		assertEquals(new BankNote(100, BAHT,100001), m.createMoney("100"));
		assertEquals(new BankNote(500, BAHT,100002), m.createMoney(500));
		assertEquals(new BankNote(1000, BAHT,100003), m.createMoney("1000"));
	}

	/** test make Malay money */
	@Test
	public void testCreateMalayMoney() {
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory m = MoneyFactory.getInstance();
		assertEquals(new Coin(0.05, "Ringgit"), m.createMoney(0.05));
		assertEquals(new Coin(0.1, "Ringgit"), m.createMoney(0.1));
		assertEquals(new Coin(0.20, "Ringgit"), m.createMoney("0.20"));
		assertEquals(new Coin(0.5, "Ringgit"), m.createMoney(0.5));
		assertEquals(new BankNote(1, RINGGIT,100000), m.createMoney("1"));
		assertEquals(new BankNote(10, RINGGIT,1000001), m.createMoney("10"));
		assertEquals(new BankNote(5, RINGGIT,1000002), m.createMoney(5));
		assertEquals(new BankNote(10, RINGGIT,1000003), m.createMoney("10"));
		assertEquals(new BankNote(20, RINGGIT,1000004), m.createMoney(20));
		assertEquals(new BankNote(50, RINGGIT,1000005), m.createMoney("50"));
		assertEquals(new BankNote(100, RINGGIT,100006), m.createMoney(100));
	}
}