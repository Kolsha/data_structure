package leetcode.singly_linked_list;

/**
 * Created by shanwu on 16-12-20.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode temp = head;

        while (temp != null) {
            ListNode next = temp.next;
            temp.next = newHead;
            newHead = temp;
            temp = next;
        }

        return newHead;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
