package test.design_pattern.adapter_pattern;

public class DuckTestDrive {
	public static void main(String[] args) {
		RedDuck duck=new RedDuck();
		WildTurkey turkey=new WildTurkey();
		
		Duck turkeyAdaptor=new TurkeyAdapter(turkey);
		System.out.println("火鸡说：");
		turkey.gobble();
		turkey.fly();
		System.out.println("鸭子说：");
		testDuck(duck);
		System.out.println("火鸡鸭子说：");
		testDuck(turkeyAdaptor);
	}

	private static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
		
	}
}
