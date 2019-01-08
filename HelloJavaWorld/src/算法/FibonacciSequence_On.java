package test.algorithm;

public class FibonacciSequence_On {

	public static int FibonacciSequence(int a,int b,int n) {
		if(n==1||n==2){
			return 1;
		}
		else if(n==3){
			return a+b;
		}
		else{
			return FibonacciSequence(b,a+b,n-1);
		}
		
	}
	public static void main(String[] args) {
		int result1=FibonacciSequence_On.FibonacciSequence(1, 1, 1);
		int result2=FibonacciSequence_On.FibonacciSequence(1, 1, 2);
		int result3=FibonacciSequence_On.FibonacciSequence(1, 1, 3);
		int result4=FibonacciSequence_On.FibonacciSequence(1, 1, 4);
		int result5=FibonacciSequence_On.FibonacciSequence(1, 1, 6);
		System.out.println(result1);//1
		System.out.println(result2);//1
		System.out.println(result3);//2
		System.out.println(result4);//3
		System.out.println(result5);//8
		
	}

}
