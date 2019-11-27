package exam.review.leetcode.singly_linked_list;



/**
 * Created by shanwu on 16-12-31.
 * PC: 1
 */
public class DeleteNodeInALinkedList {
    public class Solution {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
