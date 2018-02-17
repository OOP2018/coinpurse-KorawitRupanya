package coinpurse;

/**
 * Class to create a MoneyFactory and call its methods. Print results on the
 * console.
 * 
 * @author Korawit Rupanya
 *
 */
public class MoneyFactoryDemo {

	/**
	 * Check if MoneyFactory is a singleton,all the methods work as specified.
	 */
	public static void main(String[] args) {
		System.out.println("Test for Thai money factory");
		testThaiMoneyFactory();
		System.out.println();
		System.out.println("Test for Malay money factory");
		System.out.println();
		testMalayMoneyFactory();
		System.out.println();
		System.out.println("Test for invalid value for money factory(thaifactory)");
		testNotvalidValue();

	}

	/**
	 * Test for the singleton of Thai money factory and test create money object.
	 */
	public static void testThaiMoneyFactory() {
		MoneyFactory f1 = MoneyFactory.getInstance();
		System.out.println("f1 is a + " + f1.getClass().getName());
		MoneyFactory f2 = MoneyFactory.getInstance();
		System.out.println("f2 is a + " + f2.getClass().getName());
		System.out.println("f1 == f2 (same object ?)");
		System.out.println(f1 == f2);

		String[] coins = { "1", "2", "5", "10" };
		String[] banknotes = { "20", "50", "100", "500", "1000" };
		for (String arg : coins) {
			System.out.printf("create Money (%s) is ", arg);
			try {
				Valuable v = f1.createMoney(arg);
				System.out.println(v.toString());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}

		for (String arg : banknotes) {
			System.out.printf("create Money (%s) is ", arg);
			try {
				Valuable v = f1.createMoney(arg);
				System.out.println(v.toString());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * Test for the singleton of Malay money factory and test create money object.
	 */
	public static void testMalayMoneyFactory() {
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory f3 = MoneyFactory.getInstance();
		System.out.println("f1 is a + " + f3.getClass().getName());
		MoneyFactory f4 = MoneyFactory.getInstance();
		System.out.println("f2 is a + " + f4.getClass().getName());
		System.out.println("f1 == f2 (same object ?)");
		System.out.println(f3 == f4);

		String[] coins1 = { "0.05", "0.10", "0.20", "1", "2", "5", "10" };
		String[] banknotes1 = { "20", "50", "100" };
		for (String arg : coins1) {
			System.out.printf("create Money (%s) is ", arg);
			try {
				Valuable v = f3.createMoney(arg);
				System.out.println(v.toString());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}

		for (String arg : banknotes1) {
			System.out.printf("create Money (%s) is ", arg);
			try {
				Valuable v = f4.createMoney(arg);
				System.out.println(v.toString());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * Test create money object if can't create than throw new
	 * IllegalArgumentException.
	 */
	public static void testNotvalidValue() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory f4 = MoneyFactory.getInstance();
		String[] invalidcoin = { "60", "100", "0", "34", "75" };
		for (String arg : invalidcoin) {
			System.out.printf("create Money (%s) is ", arg);
			try {
				Valuable v = f4.createMoney(arg);
				System.out.println(v.toString());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}