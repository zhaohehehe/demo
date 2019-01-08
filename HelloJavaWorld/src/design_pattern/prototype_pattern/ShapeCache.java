package test.design_pattern.prototype_pattern;

import java.util.Enumeration;
import java.util.Hashtable;

public class ShapeCache {
	private static Hashtable<String, Shape> shapeMap =
			new Hashtable<String, Shape>();
	public static Shape getShape(String shapeId){
		Shape cachedShape = shapeMap.get(shapeId);
		return (Shape) cachedShape.colne();
	}
	// 对每种形状都运行数据库查询，并创建该形状
	// shapeMap.put(shapeKey, shape);
	// 例如，我们要添加三种形状
	public static void loadCache(){
		Rectangle rect=new Rectangle();
		rect.setId("1");
		shapeMap.put(rect.getId(), rect);
		Square square=new Square();
		square.setId("2");
		shapeMap.put(square.getId(), square);
	}
	

	

}
