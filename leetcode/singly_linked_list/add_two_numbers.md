### 2. Add Two Numbers

https://leetcode.com/problems/add-two-numbers/

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:
```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

Solution

Method 1: Elementary math
```
Time complexity: O(max(m, n))
Space Complexity: O(max(m, n))
```
```java
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode tmp = dummy;

        while (l1 != null || l2 != null || carry != 0) {
            int first = (l1 != null) ? l1.val : 0;
            int second = (l2 != null) ? l2.val : 0;
            int sum = first + second + carry;
            carry = 0;
            if (sum > 9) {
                sum = sum - 10;
                carry = 1;
            }
            tmp.next = new ListNode(sum);
            tmp = tmp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
```

#facebook