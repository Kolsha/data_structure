package exam.review.leetcode.singly_linked_list;


/**
 * Created by shanwu on 17-3-6.
 */
public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // when we have odd nodes in the list
        if(fast!=null) {
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        while(slow!=null) {
            if(slow.val!=fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    public static ListNode reverse(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode temp = head;
        ListNode nxt = null;
        ListNode pre = null;
        while(temp!=null) {
            nxt = temp.next;

            temp.next = pre;
            pre = temp;

            temp = nxt;
        }

        return pre;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
