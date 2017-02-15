package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-1-4.
 * PC: 1
 */
public class LinkedListCycles {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {

            runner = runner.next.next;
            walker = walker.next;
            if (walker == runner) {
                return true;
            }
        }
        return false;

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
