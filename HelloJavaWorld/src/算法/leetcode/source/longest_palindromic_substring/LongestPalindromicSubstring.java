package zhaohe.study.leetcode.source.longest_palindromic_substring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubstring {
	public static void main(String[] args) {
		System.out.println(LongestPalindromicSubstring.myLongestPalindrome(""));
		System.out.println(LongestPalindromicSubstring.myLongestPalindrome("abb"));
		System.out.println(LongestPalindromicSubstring.myLongestPalindrome("aaa"));
		System.out.println(LongestPalindromicSubstring.myLongestPalindrome("babad"));
		System.out.println(LongestPalindromicSubstring.myLongestPalindrome("abcda4adcba"));
	}

	/**
	 * 
	 * 时间复杂度：O(n*n) 平均执行时间：1489 ms
	 * 如果子串Ai-1,Ai,Ai+1是回文，如果Ai-2=Ai+2,那么Ai-2,Ai-1,Ai,Ai+1,Ai+2也是回文
	 * 
	 * @param s
	 * @return
	 */
	public static String myLongestPalindrome(String s) {
		if (s.equals("")) {
			return "";
		}
		int start = 0;
		int end = 0;
		Map<Character, ArrayList<Integer>> map = new HashMap<>();
		/*
		 * 遍历给定字符串，将每一个字符出现的位置以List形式记录在value中；
		 * 判断List中每一个位置与当前字符（List所对应的key一定等于当前字符）的位置是否是回文串，如果是修改start和end位置：如果不是
		 * ，继续遍历即可；
		 */
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(s.charAt(i), list);
			} else {
				for (int indexStart : map.get(s.charAt(i))) {
					if (isPalindrome(s.substring(indexStart, i + 1))) {
						// 下一个比较位置
						indexStart--;
						int indexEnd = i + 1;
						/*
						 * 如果下一个位置导致数组越界，停止比较;如果没有越界，继续比较
						 */
						if (indexStart < 0 || indexEnd > s.length() - 1) {// 下一个位置导致数组越界
							indexEnd--;
							indexStart++;
						} else {// 下一个是合理的位置，没有越界
							while (indexStart >= 0 && indexEnd <= s.length() - 1) {
								if (s.charAt(indexStart) == s.charAt(indexEnd)) {
									indexStart--;
									indexEnd++;
								} else {
									break;
								}
							}
							indexStart++;
							indexEnd--;
						}
						// 当前的回文子串是否比前一个大
						if (indexEnd - indexStart > end - start) {
							start = indexStart;
							end = indexEnd;
						}
					} else {

					}
				}
				map.get(s.charAt(i)).add(i);

			}
		}
		return s.substring(start, end + 1);
	}

	public static boolean isPalindrome(String s) {

		for (int i = 0; i <= (s.length() - 1) / 2; i++) {
			if (!(s.charAt(i) == s.charAt(s.length() - 1 - i))) {
				return false;
			}
		}
		return true;

	}
}
