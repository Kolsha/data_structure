### 82. Remove Duplicates from Sorted List II

https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Return the linked list sorted as well.

Example 1:
```
Input: 1->2->3->3->4->4->5
Output: 1->2->5
```
Example 2:
```
Input: 1->1->1->2->3
Output: 2->3
```

Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode prev = null;
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        while(curr!=null) {
            boolean isSamePrev = (prev!=null)? prev.val == curr.val : false;
            boolean isSameNext = (curr.next!=null)? curr.next.val == curr.val : false;

            if(!isSamePrev && !isSameNext) {
                temp.next = curr;
                temp = temp.next;
            }

            prev = curr;
            curr = curr.next;
        }
        temp.next = null;
        return res.next;
    }
}
```