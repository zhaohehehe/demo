package test.design_pattern.abstract_factory_pattren;

public abstract class ShapeFactory extends AbstractFactory{
	@Override
	public Shape getShape(String shapeType){
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
	@Override
	public Color getColor(String colorType){
		return null;
	}
}
