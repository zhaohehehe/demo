package test.design_pattern.builder_pattern;

public abstract class ColdDrink implements Item {


	@Override
	public Packing packing() {
		// TODO Auto-generated method stub
		return new Bottle();
	}

	public abstract float price();

}
