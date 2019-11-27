package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-2-15.
 * PC: 1
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        ListNode result = new ListNode(0);
        ListNode mod = result;
        int carry = 0;
        int sum = 0;

        while(l1!=null|| l2!=null || carry!=0) {

            sum = carry + sum;
            carry = 0;

            if(l1!=null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2!=null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if(sum >= 10) {
                sum = sum -10;
                carry = 1;
            }

            ListNode newNode = new ListNode(sum);
            mod.next = newNode;

            sum = 0;
            mod = newNode;
        }

        return result.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
