### 21. Merge Two Sorted Lists

https://leetcode.com/problems/merge-two-sorted-lists/

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while(l1!=null || l2!=null) {
            int l1Value = (l1 == null)? Integer.MAX_VALUE : l1.val;
            int l2Value = (l2 == null)? Integer.MAX_VALUE: l2.val;
            int minValue = 0;
            if(l1Value <= l2Value) {
                minValue = l1Value;
                l1 = l1.next;
            } else {
                minValue = l2Value;
                l2 = l2.next;
            }
            tmp.next = new ListNode(minValue);
            tmp = tmp.next;
        }
        return head.next;
    }
}
```

#facebook