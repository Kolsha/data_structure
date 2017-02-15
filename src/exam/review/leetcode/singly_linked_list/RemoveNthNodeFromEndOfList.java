package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-2-15.
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h1 = head, h2 = head;

        while (n-- > 0) {
            h2 = h2.next;
        }

        if (h2 == null) return head.next;  // The head need to be removed, do it.
        h2 = h2.next;

        while (h2 != null) {
            h1 = h1.next;
            h2 = h2.next;
        }
        h1.next = h1.next.next;   // the one after the h1 need to be removed
        return head;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
