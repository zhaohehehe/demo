package 内部类.内部类练习;

import java.util.Date;

//使用匿名内部类时，我们必须是继承一个类或者实现一个接口，但是两者不可兼得，同时也只能继承一个类或者实现一个接口
//匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法
class AnonymousInnerClass{
	
	@SuppressWarnings("deprecation")
	public String get(Date date){
		return date.toLocaleString();
	}
}
public class AnonymousInnerClassTest01 {

	public static void main(String[] args) {
		AnonymousInnerClass a=new AnonymousInnerClass();
		//a.get(new Date());
		String str=a.get(new Date(){
			public String toLocaleString(){
				return "hello"+super.toString();
			}
		});
		System.out.println(str);

	}
}
