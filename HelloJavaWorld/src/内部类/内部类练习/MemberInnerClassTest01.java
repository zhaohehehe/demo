package 内部类.内部类练习;

class OuterClass1{
	
	private static int a=8;
	
	public class MemberInnerClass{
		private int a=7;
		public void test(){
			System.out.println(a);//7
			System.out.println(OuterClass1.this.a);//8
		}
	}
	public MemberInnerClass getInnerClass(){
		return this.new MemberInnerClass();
	}
}
public class MemberInnerClassTest01 {

	public static void main(String[] args) {
		OuterClass1.MemberInnerClass memberClass=new OuterClass1().new MemberInnerClass();
		memberClass.test();
	}

}
