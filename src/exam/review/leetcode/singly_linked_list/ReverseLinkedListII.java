package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-1-6.
 */
public class ReverseLinkedListII {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode temp = head;
        int diff = n - m + 1;

        ListNode preNode = null;

        // move to the start point
        while (temp != null && m-- > 1) {
            preNode = temp;
            temp = temp.next;
        }

        ListNode postNode = null;

        ListNode next = null;
        ListNode pre = null;

        // start to reverse the list
        while (temp != null && diff-- > 0) {
            next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
            postNode = temp;
        }


        if (preNode != null) {
            if (preNode.next != null) {
                preNode.next.next = postNode;
            }
            preNode.next = pre;
            return preNode;
        }

        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
        }
        System.out.print('\n');
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(5);
        a.next = b;


        //reverseBetween(a, 1, 2);

        printList(reverseBetween(a, 1, 2));

    }
}
