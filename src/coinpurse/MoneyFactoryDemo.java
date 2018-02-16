package coinpurse;

public class MoneyFactoryDemo{

	public static void main(String[] args) {
		testMoneyFactory();
	
}
	

	public static void testMoneyFactory(){
		MoneyFactory f1 = MoneyFactory.getInstance();
		System.out.println("f1 is a + "+f1.getClass().getName());
		MoneyFactory f2 = MoneyFactory.getInstance();
		System.out.println("f2 is a + "+f2.getClass().getName());
		System.out.println("f1 == f2 (same object ?)");
		System.out.println(f1==f2);
		
		String [] values = {"1","2","5","10"};
		for(String arg : values) {
			System.out.printf("create Money (%s) is",arg);
			try {
				Valuable v = f1.createMoney(arg);
				System.out.println(v.toString());
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
	}
	}}

