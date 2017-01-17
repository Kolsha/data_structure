package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-1-18.
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        ListNode revTemp1 = null;
        while (temp1 != null) {
            ListNode nextNode = temp1.next;
            temp1.next = revTemp1;
            revTemp1 = temp1;
            temp1 = nextNode;
        }

        ListNode revTemp2 = null;
        while (temp2 != null) {
            ListNode nextNode = temp2.next;
            temp2.next = revTemp2;
            revTemp2 = temp2;
            temp2 = nextNode;
        }

        ListNode result = null;
        int carry = 0;
        int temp = 0;

        while (revTemp1 != null || revTemp2 != null || carry != 0) {
            temp = 0;
            if (revTemp1 != null) {
                temp += revTemp1.val;
                revTemp1 = revTemp1.next;
            }

            if (revTemp2 != null) {
                temp += revTemp2.val;
                revTemp2 = revTemp2.next;
            }

            temp = temp + carry;
            carry = 0;

            if (temp >= 10) {
                temp = temp % 10;
                carry = 1;
            }

            ListNode newNode = new ListNode(temp);
            newNode.next = result;
            result = newNode;
        }

        return result;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
