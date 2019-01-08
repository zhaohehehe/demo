package JAVA基础入门;

public interface IEat {
	public void eat();
	public void sleep();
	//default目的是为了解决接口的修改与现有的实现不兼容的问题
	public default void eatApple(){System.out.println("动物吃苹果啦");}
	public static  void eatOrange(){System.out.println("动物吃橘子啦");}
	public static void main(String[] args) {
		IEat.eatOrange();//static可以再同文件中被访问
	}
}
