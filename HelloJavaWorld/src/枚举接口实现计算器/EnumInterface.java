package 枚举接口实现计算器;

interface Calculate{
	int eval(int a,int b);
}
public enum EnumInterface implements Calculate{
	ADD("加法"){
		@Override
		public int eval(int a,int b) {
			return a+b;
		}
	},
	MINUS("减法"){
		@Override
		public int eval(int a,int b) {
			return a-b;
		}
	};
	private final String name;
	private EnumInterface(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public static void main(String[] args) {
		EnumInterface.ADD.eval(1,2);
	}
	
}
