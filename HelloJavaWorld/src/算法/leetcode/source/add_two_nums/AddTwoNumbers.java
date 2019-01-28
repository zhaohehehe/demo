package zhaohe.study.leetcode.source.add_two_nums;

/**
 * 辅助数据结构
 * @author ZH
 *
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class AddTwoNumbers {
	public static void main(String[] args) {
		// 测试数据1
		/*ListNode l1 = new ListNode(0);
		l1.next = new ListNode(1);
		ListNode l2 = new ListNode(0);
		l2.next=new ListNode(1);
		l2.next.next=new ListNode(2);*/
		
		// 测试数据2
		ListNode l1 = null;
		ListNode l2 = new ListNode(0);
		l2.next = new ListNode(1);
		
		// 测试数据3
		/*ListNode l1 = new ListNode(9);
		l1.next = new ListNode(9);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(1);*/

		/*ListNode result = AddTwoNumbers.addTwoNumbers(l1, l2);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}*/

		ListNode result = AddTwoNumbers.addTwoNumbersEncapsulation(l1, l2);//封装的myAddTwoNumbers(ListNode l1, ListNode l2)方法
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
	
	/**
	 * leetcode方法
	 * 时间复杂度：O(max(m,n))
	 * 空间复杂度：O(max(m,n))
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
	}

	public static ListNode myAddTwoNumbers(ListNode l1, ListNode l2) {
		// 进位
		int preAdd = 0;
		ListNode current1 = l1;
		ListNode current2 = l2;
		ListNode preNode1 = null;
		ListNode preNode2 = null;
		while (current1 != null && current2 != null) {
			int value = current1.val + current2.val + preAdd;
			int add = 0;
			if (value - 10 >= 0) {
				add = 1;
				value = value - 10;
			}
			// 将2个链表都更改为最新的值，因为此时不知道哪个链表长度大
			current1.val = value;
			current2.val = value;
			preAdd = add;
			preNode1 = current1;
			preNode2 = current2;
			current1 = current1.next;
			current2 = current2.next;
		}
		if (current1 == null && current2 != null) {
			while (current2 != null) {
				int value = current2.val + preAdd;
				int add = 0;
				if (value - 10 >= 0) {
					add = 1;
					value = value - 10;
				}
				current2.val = value;
				preAdd = add;
				preNode2 = current2;
				current2 = current2.next;
			}
			if (preAdd > 0) {
				preNode2.next = new ListNode(preAdd);
			}
			return l2;
		}
		if (current2 == null && current1 != null) {
			while (current1 != null) {
				int value = current1.val + preAdd;
				int add = 0;
				if (value - 10 >= 0) {
					add = 1;
					value = value - 10;
				}
				current1.val = value;
				preAdd = add;
				preNode1 = current1;
				current1 = current1.next;
			}
			if (preAdd > 0) {
				preNode1.next = new ListNode(preAdd);
			}
			return l1;
		}
		if (current1 == null && current2 == null) {
			if (preAdd > 0) {
				preNode1.next = new ListNode(preAdd);
			}
		}
		return l1;
	}

	/**
	 * JAVA分为值传递和引用传递； 基本数据类型都是值传递，但是堆对象内保存的变量是引用传递，但是对于该对象本身依然是值传递，
	 * 直接修改对象的引用（类似于C++指针），不会使对象内保存的变量发生改变
	 * 
	 * @param args
	 */
	public static void testTtrans(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode nodep1 = node1;
		ListNode nodep2 = node2;
		test(nodep1, nodep2);
		System.out.println(nodep1.val);

	}

	private static void test(ListNode nodep1, ListNode nodep2) {
		nodep1.val = 120;
		nodep1 = nodep2;
	}

	public static ListNode addTwoNumbersEncapsulation(ListNode l1, ListNode l2) {
		// 进位
		int preAdd = 0;
		ListNode current1 = l1;
		ListNode current2 = l2;
		ListNode preNode1 = current1;
		ListNode preNode2 = current2;
		while (current1 != null && current2 != null) {
			preAdd = AddTwoNumbers.preAdd(preAdd, current1, current2);
			preNode1 = current1;
			preNode2 = current2;
			current1 = current1.next;
			current2 = current2.next;
		}
		if (current1 == null && current2 != null) {
			while (current2 != null) {
				preAdd = AddTwoNumbers.preAdd(preAdd, current1, current2);
				preNode2 = current2;
				current2 = current2.next;
			}
			if (preAdd > 0) {
				preNode2.next = new ListNode(preAdd);
			}
			return l2;
		}
		if (current2 == null && current1 != null) {
			while (current1 != null) {
				preAdd = AddTwoNumbers.preAdd(preAdd, current1, current2);
				preNode1 = current1;
				current1 = current1.next;
			}
			if (preAdd > 0) {
				preNode1.next = new ListNode(preAdd);
			}
			return l1;
		}
		if (current1 == null && current2 == null) {
			if (preAdd > 0) {
				preNode1.next = new ListNode(preAdd);
			}
		}
		return l1;
	}

	/**
	 * 计算进位，同时更新current的值
	 * 
	 * @param l1
	 * @param l2
	 */
	private static int preAdd(int preAdd, ListNode current1, ListNode current2) {
		int value = (current1 == null ? 0 : current1.val) + (current2 == null ? 0 : current2.val) + preAdd;
		int add = 0;
		if (value - 10 >= 0) {
			add = 1;
			value = value - 10;
		}
		preAdd = add;
		// 将2个链表都更改为最新的值，因为此时不知道哪个链表长度大
		if (current1 != null) {
			current1.val = value;
		}
		if (current2 != null) {
			current2.val = value;
		}
		preAdd = add;
		return preAdd;
	}
}
