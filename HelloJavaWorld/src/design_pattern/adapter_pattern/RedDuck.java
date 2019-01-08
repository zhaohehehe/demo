package test.design_pattern.adapter_pattern;

public class RedDuck implements Duck {

	@Override
	public void quack() {
		System.out.println("鸭子在呱呱叫");
	}

	@Override
	public void fly() {
		System.out.println("鸭子在飞");

	}

}
