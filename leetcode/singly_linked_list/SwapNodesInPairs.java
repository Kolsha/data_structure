package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 17-1-4.
 * PC: 1
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode result = head;
        boolean isSwap = true;
        while (result != null) {
            if (isSwap && result.next != null) {
                // swap value only
                int temp = result.val;
                result.val = result.next.val;
                result.next.val = temp;
            }
            isSwap = !isSwap;
            result = result.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
