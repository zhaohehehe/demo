package test.algorithm;

import java.util.Scanner;

import test.exception.MyFirstException;

/**
 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不 死，问每个月的兔子总数为多少？
 * 时间复杂度为O(2^n);
 * 
 * @author zhaohe 2016年8月13日 下午7:02:22
 *         无论是否return位置在哪，最终结果最好在finally语句块中写，其他位置最好不写！！！
 */
public class FibonacciSequence_O2n {
	public int fiboImpl(int month) throws Exception {
		if (month <= 0) {
			throw new MyFirstException("月份小于或等于0！");
		}
		if (month == 1 || month == 2) {
			return 1;
		}
		return fiboImpl(month - 1) + fiboImpl(month - 2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		FibonacciSequence_O2n fs = new FibonacciSequence_O2n();
		int number = 0;
		int month = 0;
		if (in.hasNext()) {
			try {
				// in.nextInt(int radix);参数是进制数，默认十进制
				month = in.nextInt();
				number = fs.fiboImpl(month);
				// 抛出异常后，"(语句1)"不会执行
				// System.out.println("第"+month+"个月的兔子有"+number+"对！"+"(语句1)");
				return;
				// 加return后，如果没有异常，"(语句3)"不执行
				// 加return后，如果有异常，"(语句1)"不执行
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// 无论是否抛出异常，finally"(语句2)"都会执行
				System.out.println("第" + month + "个月的兔子有" + number + "对！" + "(语句2)");
			}
			// 无论是否抛出异常，"(语句3)"都会执行
			// System.out.println("第"+month+"个月的兔子有"+number+"对！"+"(语句3)");
		}

	}

}
