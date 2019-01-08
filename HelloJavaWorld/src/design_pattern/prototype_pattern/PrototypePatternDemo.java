package test.design_pattern.prototype_pattern;

public class PrototypePatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeCache.loadCache();
		
		Shape cloneShape1 = ShapeCache.getShape("1");
		System.out.println("Shape:"+cloneShape1.getType());
		
		Shape cloneShape2 = ShapeCache.getShape("1");
		System.out.println("Shape:"+cloneShape2.getType());
		
		Shape cloneShape3 = ShapeCache.getShape("2");
		System.out.println("Shape:"+cloneShape3.getType());
		
		System.out.println(cloneShape1.equals(cloneShape2));//false
		System.out.println(cloneShape1==cloneShape2);//false
	}

}
