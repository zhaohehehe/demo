package 内部类.内部类练习;


class OuterClass2{
	
	public void doSomething(){
	    int a=9;
		class LocalInnerClass{
			public void test(){
				System.out.println(a);
			}
		}
		new LocalInnerClass().test();
	}
}
public class LocalInnerClassTest01 {

	public static void main(String[] args) {
		OuterClass2 outerClass=new OuterClass2();
		outerClass.doSomething();
	}

}
