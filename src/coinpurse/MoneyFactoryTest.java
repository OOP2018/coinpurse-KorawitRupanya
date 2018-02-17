package coinpurse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Money Factory test 
 * @author Korawit Rupanya
 */

public class MoneyFactoryTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	/**Thai currency*/
	private final String BAHT = "Baht";
	/**malay currency*/
    private final String RINGGIT = "Ringgit";

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}
	/**
	 * Make money by the value as double
	 * @param value is the value that is used to make money.
	 * @return the valuable as the money.
	 */
	public Valuable makeMoney(double value) {
		try {
            return MoneyFactory.getInstance().createMoney(value);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
	/**
	 * Make money by the value as double.
	 * @param value is the value that is used to make money.
	 * @return true if can make money ,false if it can't.
	 */
	 public boolean testMakeMoneyByValue(double value){
        try {
            makeMoney(value);
            return true;
        }catch (IllegalArgumentException e ){
            return false;
        }
    }
	/**
	 * Make money by the value as string
	 * @param value is the value that is used to make money.
	 * @return the valuable as the money.
	 */
	public Valuable makeMoney(String value) {
		try {
            return MoneyFactory.getInstance().createMoney(value);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
	/**
	 * Make money by the value as string.
	 * @param value is the value that is used to make money.
	 * @return true if can make money ,false if it can't.
	 */
	 public boolean testMakeMoneyByValue(String value){
        try {
            makeMoney(value);
            return true;
        }catch (IllegalArgumentException e ){
            return false;
        }
    }
	/**
	 * Test if the factory can get the instance.
	 */
	@Test
	public void testGetInstance() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory m = MoneyFactory.getInstance();
		assertEquals("coinpurse.ThaiMoneyFactory",m.getClass().getName());
		assertEquals(ThaiMoneyFactory.getInstance(), m);
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory m1 = MoneyFactory.getInstance();
		assertEquals("coinpurse.MalayMoneyFactory",m1.getClass().getName());
		assertEquals(MalayMoneyFactory.getInstance(), m1);
	}
	/** test make Thai money */
	@Test
	public void testCreatThaiMoney() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory m = MoneyFactory.getInstance();
		assertEquals(new Coin(1,BAHT),m.createMoney(1));
        assertEquals(new Coin(2,BAHT),m.createMoney("2"));
        assertEquals(new Coin(5,BAHT),m.createMoney(5));
        assertEquals(new Coin(10,BAHT),m.createMoney("10"));
        assertEquals(new BankNote(20,BAHT),m.createMoney(20));
        assertEquals(new BankNote(100,BAHT),m.createMoney("100"));
        assertEquals(new BankNote(500,BAHT),m.createMoney(500));
        assertEquals(new BankNote(1000,BAHT),m.createMoney("1000"));
	}
	/** test make Malay money */
    @Test
    public void testCreateMalayMoney(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory m = MoneyFactory.getInstance();
        assertEquals(new Coin(5,"Sen coin"),m.createMoney(0.05));
        assertEquals(new Coin(10,"Sen coin"),m.createMoney(0.1));
        assertEquals(new Coin(20,"Sen coin"),m.createMoney("0.20"));
        assertEquals(new Coin(50,"Sen coin"),m.createMoney(0.5));
        assertEquals(new BankNote(1,RINGGIT),m.createMoney("1"));
        assertEquals(new BankNote(10,RINGGIT),m.createMoney("10"));
        assertEquals(new BankNote(5,RINGGIT),m.createMoney(5));
        assertEquals(new BankNote(10,RINGGIT),m.createMoney("10"));
        assertEquals(new BankNote(20,RINGGIT),m.createMoney(20));
        assertEquals(new BankNote(50,RINGGIT),m.createMoney("50"));
        assertEquals(new BankNote(100,RINGGIT),m.createMoney(100));
    }
}