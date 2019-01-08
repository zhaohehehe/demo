package test.design_pattern.factory_pattern;

public class ShapeFactory {
	public Shape getShage(String shapeType){
		if(shapeType==null){
			return null;
		}
		if(shapeType.equalsIgnoreCase("rectangle")){
			return new Rectangle();
		}
		if(shapeType.equalsIgnoreCase("circle")){
			return new Circle();
		}
		return null;
	}
}
