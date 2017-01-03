package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-1-3.
 */
public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        if(temp1 == null && temp2 == null) {
            return null;
        } else if (temp1 == null) {
            return temp2;
        } else if (temp2 == null) {
            return temp1;
        }

        ListNode temp1Next = null;
        ListNode temp2Next = null;

        ListNode result = null;

        while (temp1 != null && temp2 != null) {
            temp1Next = temp1.next;
            temp2Next = temp2.next;

            if (temp1 != null && temp2 != null && temp1.val <= temp2.val) {
                temp1.next = result;
                result = temp1;
                temp1 = temp1Next;
            } else if (temp1 != null && temp2 != null && temp2.val < temp1.val) {
                temp2.next = result;
                result = temp2;
                temp2 = temp2Next;
            }
        }


        while (temp1 != null) {
            temp1Next = temp1.next;
            temp1.next = result;
            result = temp1;

            temp1 = temp1Next;
        }

        while (temp2 != null) {
            temp2Next = temp2.next;

            temp2.next = result;
            result = temp2;

            temp2 = temp2Next;
        }

        // inverse the list
        ListNode preNode = null;
        while(result!=null) {
            temp1Next = result.next;
            result.next = preNode;
            preNode = result;
            result = temp1Next;
        }
        return preNode;
    }


    public static void main(String[] args) {
        ListNode zero = new ListNode(0);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.next = three;
        three.next = four;

        ListNode result = mergeTwoLists(one, zero);
        while(result!=null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
