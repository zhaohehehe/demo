package test.design_pattern.factory_pattern;

public class ShapeFactoryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory sf=new ShapeFactory();
		Shape shape1=sf.getShage("rectangle");
		shape1.draw();
		Shape shape2=sf.getShage("Circle");
		shape2.draw();
		System.out.println("w4e");
	}

}
