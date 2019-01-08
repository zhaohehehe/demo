package test.design_pattern.prototype_pattern;

public class Square extends Shape{
	
	public Square(){
		type="Square";
	}

	@Override
	void draw() {
		// TODO Auto-generated method stub
		System.out.println("画一个正方形method");
	}
}
