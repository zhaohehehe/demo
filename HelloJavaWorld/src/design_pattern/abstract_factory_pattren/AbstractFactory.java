package test.design_pattern.abstract_factory_pattren;

public abstract class AbstractFactory {
	abstract public Color getColor(String colorType);
	abstract public Shape getShape(String shapeType);
}
