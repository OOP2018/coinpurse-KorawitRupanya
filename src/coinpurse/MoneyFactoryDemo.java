package coinpurse;

public class MoneyFactoryDemo{

	public static void main(String[] args) {
		testMoneyFactory();
	
}
	public static void testMoneyFactory(){
		
		//Test for Thai money factory
		MoneyFactory f1 = MoneyFactory.getInstance();
		System.out.println("f1 is a + "+f1.getClass().getName());
		MoneyFactory f2 = MoneyFactory.getInstance();
		System.out.println("f2 is a + "+f2.getClass().getName());
		System.out.println("f1 == f2 (same object ?)");
		System.out.println(f1==f2);
	
		String [] coins = {"1","2","5","10"};
		String [] banknotes = {"20","50","100","500","1000"};
		for(String arg : coins) {
			System.out.printf("create Money (%s) is",arg);
			try {
				Valuable v = f1.createMoney(arg);
				System.out.println(v.toString());
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
	}
		
		for(String arg : banknotes) {
			System.out.printf("create Money (%s) is",arg);
			try {
				Valuable v = f1.createMoney(arg);
				System.out.println(v.toString());
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
	}
		
		//Test for Malai money factory
		MoneyFactory.setFactory(new MalaiMoneyFactory());
		MoneyFactory f3 = MoneyFactory.getInstance();
		System.out.println("f1 is a + "+f3.getClass().getName());
		MoneyFactory f4 = MoneyFactory.getInstance();
		System.out.println("f2 is a + "+f4.getClass().getName());
		System.out.println("f1 == f2 (same object ?)");
		System.out.println(f3==f4);
	
		String [] coins1 = {"1","2","5","10"};
		String [] banknotes1 = {"20","50","100","500","1000"};
		for(String arg : coins1) {
			System.out.printf("create Money (%s) is",arg);
			try {
				Valuable v = f3.createMoney(arg);
				System.out.println(v.toString());
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
	}
		
		for(String arg : banknotes1) {
			System.out.printf("create Money (%s) is",arg);
			try {
				Valuable v = f4.createMoney(arg);
				System.out.println(v.toString());
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
	}
		
		
	}}

