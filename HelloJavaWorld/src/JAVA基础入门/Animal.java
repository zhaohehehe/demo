package JAVA基础入门;

/*public class Animal implements IEat{
	public String name=null;
	public void eat(){System.out.println("动物吃水果啦");}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IEat an=new Animal();
		an.eatApple();
		//an.eatOrange();
		//error,static只能在同源文件中被访问
		an.eat();
	}
}
*/
//抽象类可以在不提供接口方法实现的情况下实现接口
public abstract class Animal implements IEat{
	public String name=null;
	public void eat(){System.out.println("动物吃水果啦");}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

