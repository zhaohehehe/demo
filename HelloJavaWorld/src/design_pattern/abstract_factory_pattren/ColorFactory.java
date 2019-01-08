package test.design_pattern.abstract_factory_pattren;

public abstract class ColorFactory extends AbstractFactory{
	@Override
	public Color getColor(String typeColor) {
		// TODO Auto-generated method stub
		if(typeColor==null){
			return null;
		}
		if(typeColor.equalsIgnoreCase("red")){
			return new Red();
		}
		else if(typeColor.equalsIgnoreCase("black")){
			return new Black();
		}
		return null;
	}

	@Override
	public Shape getShape(String shapeType) {
		// TODO Auto-generated method stub
		return null;
	}

}
