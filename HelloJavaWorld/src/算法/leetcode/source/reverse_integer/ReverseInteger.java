package zhaohe.study.leetcode.source.reverse_integer;

public class ReverseInteger {
	public static void main(String[] args) {
		System.out.println(ReverseInteger.reverse(-123));
		System.out.println(ReverseInteger.reverse(123));
		System.out.println(ReverseInteger.reverse(0));
		System.out.println(ReverseInteger.reverse(1534236469));// 0
		System.out.println(ReverseInteger.reverse(1463847412));// 2147483641

	}

	public static int reverse(int x) {
		if (x < Integer.MIN_VALUE || x > Integer.MAX_VALUE) {
			return 0;
		}
		int sum = 0;
		int a = x;
		int b = 10;
		int c = a / b;
		int d = a % b;
		sum = d;
		while (c != 0) {
			a = c;
			c = a / b;
			d = a % b;
			/*
			 * 这里需要提前判断是否越界，因为一但越界就已经出错了
			 */
			if ((sum - d) <= Integer.MIN_VALUE / 10 || (sum - d) >= Integer.MAX_VALUE / 10) {
				return 0;
			}
			sum = sum * b + d;
		}
		return sum;
	}
}
