package zhaohe.study.leetcode.source.atoi;

public class A2I {
	public static void main(String[] args) {
		// System.out.println(Integer.MAX_VALUE);// 2147483647
		// System.out.println(Integer.MIN_VALUE);// -2147483648
		// System.out.println(2147483647 + 1);// -2147483648
		// System.out.println(2147483647 + 1);// -2147483648
		System.out.println(
				A2I.myAtoi("-2147483647") == A2I.myAtoi2("-2147483647") && A2I.myAtoi("-2147483647") == -2147483647);
		System.out.println(A2I.myAtoi("-0000000000000012") == A2I.myAtoi2("-0000000000000012")
				&& A2I.myAtoi("-0000000000000012") == -12);
		System.out.println(A2I.myAtoi("-000000000000000091283472332") == A2I.myAtoi2("-000000000000000091283472332")
				&& A2I.myAtoi("-000000000000000091283472332") == -2147483648);
		System.out.println(A2I.myAtoi("+0000000000000012") == A2I.myAtoi2("+0000000000000012")
				&& A2I.myAtoi("+0000000000000012") == 12);
		System.out.println(A2I.myAtoi("+000000000000000091283472332") == A2I.myAtoi2("+000000000000000091283472332")
				&& A2I.myAtoi("+000000000000000091283472332") == 2147483647);
		System.out.println(A2I.myAtoi("-91283472332") == -2147483648);
		System.out.println(A2I.myAtoi("  0000000000012345678") == 12345678);
		System.out.println(A2I.myAtoi("2147483648") == 2147483647);
		System.out.println(A2I.myAtoi(" -42") == -42);
		System.out.println(A2I.myAtoi(" -3.14159") == -3);
		System.out.println(A2I.myAtoi("+- 3.14159") == 0);
		System.out.println(A2I.myAtoi("+3.14159") == 3);
		System.out.println(A2I.myAtoi("3.14159") == 3);
		System.out.println(A2I.myAtoi("words and 987") == 0);
		System.out.println(A2I.myAtoi("42") == 42);
		System.out.println(A2I.myAtoi("193 with words") == 193);
		System.out.println(A2I.myAtoi("and -987") == 0);
		System.out.println(A2I.myAtoi("-91283472332") == -2147483648);

	}

	public static int myAtoi(String str) {
		boolean isConversion = false;
		boolean isAdd = false;
		boolean isMinus = false;
		int start = 0;
		int end = str.length() - 1;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!isConversion) {// 未遇到数字
				if (isAdd || isMinus) {
					if (ch >= '0' && ch <= '9') {
						start = i - 1;
						end = i;
						isConversion = true;
						continue;
					} else {
						isAdd = false;
						isMinus = false;
						break;
					}
				} else {
					if (ch == ' ') {
						continue;
					}
					if (ch == '+') {
						isAdd = true;
						continue;
					}
					if (ch == '-') {
						isMinus = true;
						continue;
					}
					if (ch >= '0' && ch <= '9') {
						start = i;
						isConversion = true;
						continue;
					}
					break;
				}
			} else {// 已经遇到数字，寻找end
				if (ch >= '0' && ch <= '9') {
					end = i;
					continue;
				} else {
					if (isConversion) {
						end = i - 1;
					}
					break;
				}
			}
		}
		if (!isConversion) {
			return 0;
		}
		// ========================================================================================
		// !!!!这里sum的类型不需要比int的范围大，因为有判断整数位数
		// ========================================================================================
		int sum = 0;
		// 重置
		isMinus = false;
		if (str.charAt(start) == '-') {
			start++;
			isMinus = true;
		} else if (str.charAt(start) == '+') {
			start++;
		}
		for (int i = start; i <= end; i++) {
			int number = str.charAt(i) - '0';
			if (isMinus && (i - start + 1) >= 10) {
				if ((-sum == Integer.MIN_VALUE / 10 && -number < Integer.MIN_VALUE % 10)
						|| -sum < Integer.MIN_VALUE / 10) {
					return Integer.MIN_VALUE;
				}
			} else if (!isMinus && (i - start + 1) >= 10) {
				if ((sum == Integer.MAX_VALUE / 10 && number > Integer.MAX_VALUE % 10)
						|| sum > Integer.MAX_VALUE / 10) {
					return Integer.MAX_VALUE;
				}
			} else {

			}
			sum = sum * 10 + number;

		}
		if (isMinus) {
			sum = -sum;
		}
		return sum;

	}

	public static int myAtoi2(String str) {
		boolean isConversion = false;
		boolean isAdd = false;
		boolean isMinus = false;
		int start = 0;
		int end = str.length() - 1;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!isConversion) {// 未遇到数字
				if (isAdd || isMinus) {
					if (ch >= '0' && ch <= '9') {
						start = i - 1;
						end = i;
						isConversion = true;
						continue;
					} else {
						isAdd = false;
						isMinus = false;
						break;
					}
				} else {
					if (ch == ' ') {
						continue;
					}
					if (ch == '+') {
						isAdd = true;
						continue;
					}
					if (ch == '-') {
						isMinus = true;
						continue;
					}
					if (ch >= '0' && ch <= '9') {
						start = i;
						isConversion = true;
						continue;
					}
					break;
				}
			} else {// 已经遇到数字，寻找end
				if (ch >= '0' && ch <= '9') {
					end = i;
					continue;
				} else {
					if (isConversion) {
						end = i - 1;
					}
					break;
				}
			}
		}
		if (!isConversion) {
			return 0;
		}
		// ========================================================================================
		// !!!!这里sum的类型一定要保证比int的范围大，因为sum在以下计算的时候可能超出int长度（当输入值为-2147483648或者2147483648时，会计算出sum值为2147483648，越界变成-2147483648）.
		// int类型范围为[-2147483648,2147483647]
		// ========================================================================================
		long sum = 0;
		// 重置
		isMinus = false;
		if (str.charAt(start) == '-') {
			start++;
			isMinus = true;
		} else if (str.charAt(start) == '+') {
			start++;
		}
		for (int i = start; i <= end; i++) {
			if (true) {
				int number = str.charAt(i) - '0';
				sum = sum * 10 + number;
				if (isMinus && -sum < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}

				if (!isMinus && sum > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}

			}

		}
		if (isMinus) {
			sum = -sum;
		}
		return (int) sum;

	}
}
