package test.design_pattern.strategy_pattern.demo2;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println("不会叫");
	}

}
