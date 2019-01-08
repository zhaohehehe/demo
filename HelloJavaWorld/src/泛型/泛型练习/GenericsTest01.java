package 泛型.泛型练习;

/**
 * 泛型实际上是生成无数多个逻辑上的子类，但是这种子类在物理上并不存在；
 * List<Circle>不是List<Shape>的子类型，所以在Canvas方法中定义 void draw(List<Shape> shapes)是错误的，
 * 可以改成通配符的形式void draw(List<?> shapes)，但是程序变得繁琐，可以改成被限制的泛型通配符void draw(List<? extends Shape> shapes)，他可以表示
 * List<Circle>和List<Rectangle>的父类
 * @author zhaohe
 *
 * @param <T>
 * @param <E>
 */
public class GenericsTest01<T,E> 
{
	
	private T typeName;
	public T getTypeName() {
		return typeName;
	}


	public void setTypeName(T typeName) {
		this.typeName = typeName;
	}


	public E getTypeName2() {
		return typeName2;
	}


	public void setTypeName2(E typeName2) {
		this.typeName2 = typeName2;
	}


	private E typeName2;

	
	public static void main(String[] args) {
		GenericsTest01<Boolean,Integer> gene=new GenericsTest01<Boolean,Integer>();
		gene.setTypeName(false);
		gene.setTypeName2(7);
		System.out.println(gene.getTypeName());
		System.out.println(gene.getTypeName2());
		
	}
	
}
