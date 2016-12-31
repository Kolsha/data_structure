package exam.review.leetcode;


import exam.review.leetcode.singly_linked_list.ReverseLinkedList;

/**
 * Created by shanwu on 16-12-31.
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
