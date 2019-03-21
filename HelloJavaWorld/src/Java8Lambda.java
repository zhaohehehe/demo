package com.bonc.dataplatform.demo;

@FunctionalInterface
interface Operator {
	int add(int a, int b);

	default int minus(int a, int b) {
		return a - b;
	}

	static int multiply(int a, int b) {
		return a * b;
	}
}

public class Java8Lambda {
	public static void main(String[] args) {
		Java8Lambda.test2();
		Java8Lambda.test1();
	}

	public int add(int a, int b, Operator op) {
		return op.add(a, b);
	}

	public int minus(int a, int b, Operator op) {
		return op.minus(a, b);
	}

	public static void test2() {
		// 类型声明
		Operator op = (int a, int b) -> (a + b);
		System.out.println(op.add(10, 12));
		System.out.println(op.minus(12, 20));
		// 类型声明
		Operator op1 = (a, b) -> a + b;
		op1.add(10, 12);
		op1.minus(12, 20);
		// return
		Operator op2 = (a, b) -> {
			return a + b;
		};
		op2.add(10, 12);
		op2.minus(12, 20);

	}

	public static void test1() {
		Java8Lambda test = new Java8Lambda();
		// jdk8
		int result = test.add(12, 10, (int a, int b) -> {
			return a + b;
		});
		System.out.println(result);
		// pre jdk8
		result = test.add(12, 10, new Operator() {

			@Override
			public int add(int a, int b) {
				// TODO Auto-generated method stub
				return a + b;
			}
		});
		System.out.println(result);
		result = test.minus(12, 10, new Operator() {

			@Override
			public int add(int a, int b) {
				// TODO Auto-generated method stub
				return a + b;
			}

			@Override
			public int minus(int a, int b) {
				// TODO Auto-generated method stub
				return a - b + 1;
			}
		});
		System.out.println(result);

	}

}
