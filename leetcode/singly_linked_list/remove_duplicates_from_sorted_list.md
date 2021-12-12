### [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

 

Example 1:
![](https://assets.leetcode.com/uploads/2021/01/04/list1.jpg)
```
Input: head = [1,1,2]
Output: [1,2]
```
Example 2:
![](https://assets.leetcode.com/uploads/2021/01/04/list2.jpg)
```
Input: head = [1,1,2,3,3]
Output: [1,2,3]
``` 

Constraints:

- The number of nodes in the list is in the range `[0, 300]`.
- -100 <= Node.val <= 100
- The list is guaranteed to be **sorted** in ascending order.



##### Solution

##### Approach 1: Iterative

##### Complexity analysis
- Time complexity:
- Space complexity:

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

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;

        while(dummy.next != null) {
            if(dummy.next.val == dummy.val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
    
        }
        return head;
    }
}
```

##### Approach 2: Recursive

##### Complexity analysis
- Time complexity:
- Space complexity:

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
```