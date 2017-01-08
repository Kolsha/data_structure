package exam.review.leetcode.singly_linked_list;

import java.util.HashMap;

/**
 * Created by shanwu on 17-1-8.
 */
public class IntersectionOf2linkedList {

    /*
     *  fixme: Trying to use the hashmap, but it turns out not good ... bad performance
     */
    public static ListNode getIntersectionNode(ListNode A, ListNode B) {
        ListNode temp1 = A;
        ListNode temp2 = B;

        HashMap<Integer, Integer> map = new HashMap(); // val, order map

        int lenA = 1;
        while (temp1 != null) {
            if (!map.containsKey(temp1.val)) {
                map.put(temp1.val, lenA);
            }
            temp1 = temp1.next;
            lenA++;
        }

        int lenB = 1;
        int preSame = 0;
        ListNode result = null;
        ListNode start = null;
        while (temp2 != null) {
            Integer val = map.get(temp2.val);
            if (val != null) {
                ListNode newNode = new ListNode(temp2.val);
                if (start == null) {
                    start = newNode;
                    result = start;
                } else {
                    int num = val;
                    int diff = num - preSame;
                    if (diff == 1) {
                        start.next = newNode;
                        start = newNode;
                    }
                }
                preSame = val;
            }
            temp2 = temp2.next;
            lenB++;
        }

        return result;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
