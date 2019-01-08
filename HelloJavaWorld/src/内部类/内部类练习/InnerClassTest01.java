package 内部类.内部类练习;

public class InnerClassTest01 {
	private String test="outer";
	private class InClass{
		private String test="inter";
		public void info(){
			System.out.println(InnerClassTest01.this.test);
			System.out.println(this.test);
		}
	}
	public void info(){
		InClass a=new InClass();
		a.info();
	}
	public static void main(String[] args) {
		InnerClassTest01 in=new InnerClassTest01();
		in.info();
	}
}
