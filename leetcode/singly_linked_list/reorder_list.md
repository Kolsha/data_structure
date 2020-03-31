### 143. Reorder List

https://leetcode.com/problems/reorder-list/

Given a singly linked list L: L<sub>0</sub>→L<sub>1</sub>→…→L<sub>n-1</sub>→L<sub>n</sub>,
reorder it to: L<sub>0</sub>→L<sub>n</sub>→L<sub>1</sub>→L<sub>n-1</sub>→L<sub>2</sub>→L<sub>n-2</sub>→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
```
Given 1->2->3->4, reorder it to 1->4->2->3.
```
Example 2:
```
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
```

Solution

Method 1: Using Stack

Time complexity:O(n)

Space complexity: O(n)

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
    public void reorderList(ListNode head) {
        if(head == null) {
            return;
        }
        
        ListNode itr = head;
        int listSize = 0;
        while(itr!=null) {
            listSize++;
            itr = itr.next;
        }
        
        // add last half of the list to the stack
        Stack<ListNode> stack = new Stack<>();
        itr = head;
        int currId = 0;
        while(itr!=null) {
            if(currId >= listSize/2) {
                stack.push(itr);
            }
            currId++;
            itr = itr.next;
        }
        
        // find the last node for the new list
        currId = 0;
        itr = head;
        while(currId < (listSize - listSize/2 - 1)) {
            itr = itr.next;
            currId++;
        }
        itr.next = null;
        

        itr = head;
        int currSize = 0;
        itr = head;
        while(itr!=null) {
            ListNode next = itr.next;
            ListNode nodeFromStack = stack.pop();
            ListNode tmp = itr.next;
            itr.next = nodeFromStack;
            nodeFromStack.next = tmp;
            itr = next;
        }
        
    }
}
```

Solution

Method 2: One pass, find the middle node, reverse the second half of the list and merge.

Time complexity: O(n)

Space complexity: O(1)
```java
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // find the middle node
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half
        ListNode head2 = reverse(slow.next);
        slow.next = null;

        // connect them together
        ListNode head1 = head;
        while (head1 != null && head2 != null) {
            ListNode tmp1 = head1.next;
            ListNode tmp2 = head2.next;

            head2.next = head1.next;
            head1.next = head2;

            head1 = tmp1;
            head2 = tmp2;
        }

    }

    private ListNode reverse(ListNode n) {
        ListNode prev = null;
        ListNode cur = n;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
}
```