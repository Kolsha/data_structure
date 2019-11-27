package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-3-1.
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return null;
        }


        ListNode temp = head;

        while(temp.next!=null) {
            if(temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return head.val == val? head.next: head;
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
