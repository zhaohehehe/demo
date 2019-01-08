package test.design_pattern.decoration_pattern;

public class ConcreteDecorator1 extends Decorator {

	//因为父类没有无参构造方法，所以要添加下面的有参构造方法
	public ConcreteDecorator1(Component component) {
		super(component);
	}
	@Override
	public void doSomething() {
		super.doSomething();
		
		this.doAnotherthing();
	}
	private void doAnotherthing(){
		System.out.println("功能B");
	}
}