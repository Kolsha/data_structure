### 382. Linked List Random Node

https://leetcode.com/problems/linked-list-random-node/

Given a singly linked list, return a random node's value from the linked list. Each node must have the **same probability** of being chosen.

**Follow up:**
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:
```
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
```

Solution

Approach 1: Reservoir Sampling

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

    private ListNode headPtr = null;
    private Random rand = new Random();
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        headPtr = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode c = headPtr;
        int result = c.val;
        for(int i = 1; c.next!=null; i++) {
            c = c.next;
            if(rand.nextInt(i+1) == i) {
                result = c.val;
            }
        }
        return result;
    }
}
```