package com.zhaohe.utils;

public class KMP {
	public static void main(String[] args) {
		/*
		 * int[] next = KMP.getNext("aaab"); for(int i=0;i<next.length;i++){
		 * System.out.println(next[i]); }
		 */
		long a = System.currentTimeMillis();
		String str = "insert overwrite local directory hello";
		for (int i = 0; i < 10000; i++) {
			kmp(str, "ory", 0);
		}
		long b = System.currentTimeMillis();
		System.out.println(b - a);
		a = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			str.indexOf("ory");
		}
		b = System.currentTimeMillis();
		System.out.println(b - a);

	}

	// https://www.cnblogs.com/tangzhengyue/p/4315393.html
	public static int[] getNext(String pattern) {
		int[] nextVal = new int[pattern.length()];
		/*
		 * int i = 1; nextVal[1] = 0; int k = 0;
		 */
		// 使用以下参数初始化也可以，只不过while循环k值换成-1
		int i = 0;
		nextVal[0] = -1;
		int k = -1;
		while (i < pattern.length() - 1) {
			if (k == -1 || pattern.charAt(i) == pattern.charAt(k)) {
				i++;
				k++;
				if (pattern.charAt(i) != pattern.charAt(k)) {
					nextVal[i] = k;
				} else {
					nextVal[i] = nextVal[k];
				}
			} else {
				// 找其他的k位置（最小前后缀）
				k = nextVal[k];
			}
		}
		return nextVal;
	}

	public static int kmp(String main, String pattern, int pos) {
		int[] nextVal = KMP.getNext(pattern);
		int i = pos;
		int j = -1;
		while (i < main.length() && j < pattern.length()) {
			if (j == -1 || main.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				j = nextVal[j];
			}
		}
		if (j >= pattern.length()) {
			// 匹配成功
			return i - pattern.length();
		} else {
			return -1;
		}
	}

}
