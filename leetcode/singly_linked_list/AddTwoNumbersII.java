package exam.review.leetcode.singly_linked_list;

import java.util.Stack;

/**
 * Created by shanwu on 17-1-18.
 * PC: 1
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

    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> valStack1 = new Stack<>();
        Stack<Integer> valStack2 = new Stack<>();

        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null) {
            valStack1.push(temp1.val);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            valStack2.push(temp2.val);
            temp2 = temp2.next;
        }

        ListNode result = null;
        int sum = 0;
        int carry = 0;
        while (!valStack1.isEmpty() || !valStack2.isEmpty() || carry != 0) {
            int t1 = (!valStack1.isEmpty()) ? valStack1.pop() : 0;
            int t2 = (!valStack2.isEmpty()) ? valStack2.pop() : 0;
            sum += carry + t1 + t2;
            carry = 0;

            if (sum > 9) {
                sum = sum - 10;
                carry = 1;
            }

            ListNode newNode = new ListNode(sum);
            newNode.next = result;
            result = newNode;

            sum = 0;

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
