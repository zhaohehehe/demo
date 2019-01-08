package test.design_pattern.strategy_pattern.demo2;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	/*
	 * 子类可以不重写该方法
	 */
	public void swim(){}
	/*
	 * 子类必须实现该抽象方法
	 */
	public abstract void display();
	public void performFly(){
		this.flyBehavior.fly();
	}
	public void performQuack(){
		this.quackBehavior.quack();
	}
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	
	
}
