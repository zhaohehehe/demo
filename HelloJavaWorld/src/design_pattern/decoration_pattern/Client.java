package test.design_pattern.decoration_pattern;

public class Client {

	public static void main(String[] args) {
		//节点流
		Component component=new ConcreteComponent();
		//过滤流
		Component component1=new ConcreteDecorator1(component);
		//过滤流
		Component component2=new ConcreteDecorator2(component1);
		component2.doSomething();
		System.out.println("--------------");
		component1.doSomething();
		System.out.println("--------------");
		component.doSomething();
		System.out.println("-------------------");
		Component component3=new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
		component3.doSomething();
		
		
	}
}
