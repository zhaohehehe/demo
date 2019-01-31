package zhaohe.study.leetcode.source.palindrome_number;

import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber {
	public static void main(String[] args) {
		System.out.println(PalindromeNumber.leetcodeIsPalindrome(1000021));
	}

	/**
	 * 时间复杂度：O(n)
	 * 
	 * @param x
	 * @return
	 */
	public static boolean myIsPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		List<Integer> ints = new ArrayList<>();
		int a = x / 10;
		int i = 0;
		ints.add(i, x % 10);
		while (a != 0) {
			i++;
			ints.add(i, a % 10);
			a = a / 10;
		}
		for (int k = 0; k < ints.size() / 2; k++) {
			if (ints.get(k) == ints.get(ints.size() - k - 1)) {
				continue;
			} else {
				return false;
			}
		}
		return true;

	}

	/**
	 * Now let's think about how to revert the last half of the number. For
	 * number 1221, if we do 1221 % 10, we get the last digit 1, to get the
	 * second to the last digit, we need to remove the last digit from 1221, we
	 * could do so by dividing it by 10, 1221 / 10 = 122. Then we can get the
	 * last digit again by doing a modulus by 10, 122 % 10 = 2, and if we
	 * multiply the last digit by 10 and add the second last digit, 1 * 10 + 2 =
	 * 12, it gives us the reverted number we want. Continuing this process
	 * would give us the reverted number with more digits
	 * 
	 * 时间复杂度：O(log10n)
	 * 
	 * @param x
	 * @return
	 */

	public static boolean leetcodeIsPalindrome(int x) {
		// Special cases:
		// As discussed above, when x < 0, x is not a palindrome.
		// Also if the last digit of the number is 0, in order to be a
		// palindrome,
		// the first digit of the number also needs to be 0.
		// Only 0 satisfy this property.
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}

		int revertedNumber = 0;
		while (x > revertedNumber) {
			revertedNumber = revertedNumber * 10 + x % 10;
			x /= 10;
		}

		// When the length is an odd number, we can get rid of the middle digit
		// by revertedNumber/10
		// For example when the input is 12321, at the end of the while loop we
		// get x = 12, revertedNumber = 123,
		// since the middle digit doesn't matter in palidrome(it will always
		// equal to itself), we can simply get rid of it.
		return x == revertedNumber || x == revertedNumber / 10;
	}
}
