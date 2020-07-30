### 206. Reverse Linked List

https://leetcode.com/problems/reverse-linked-list/

Reverse a singly linked list.

Example:
```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```
Follow up:

- A linked list can be reversed either iteratively or recursively. Could you implement both?

Solution

Approach 1: Iteratively

Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

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
    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        while(head!=null) {
            ListNode next = head.next;
            head.next = res;
            res = head;
            head = next;
        }
        return res;
    }
}
```

Approach 2: Recursive approach
Complexity analysis
- Time complexity: O(n)
- Space complexity: O(n) -> cost made by Recursive calls

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
    public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }
    
    private ListNode reverseList(ListNode head, ListNode newHead) {
        if(head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseList(next, head);        
    }
}
```