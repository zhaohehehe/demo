package 枚举抽象方法实现计算器;
public enum EnumAbstract{
	MINUS{

		@Override
		public int eval(int a, int b) {
			// TODO Auto-generated method stub
			return a-b;
		}
	},
	ADD{

		@Override
		public int eval(int a, int b) {
			// TODO Auto-generated method stub
			return a+b;
		}

	};
	public abstract int eval(int a,int b);
	public static void main(String[] args) {
		int result=EnumAbstract.ADD.eval(1, 2);
		System.out.println(result);
	}
	
}

