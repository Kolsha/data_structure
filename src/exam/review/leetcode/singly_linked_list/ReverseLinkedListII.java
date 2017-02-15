package exam.review.leetcode.singly_linked_list;

import java.util.HashMap;

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

    /**
     * use one hash map record all nodes with index, then we make the swap
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetweenII(ListNode head, int m, int n) {
        if(head == null) {
            return head;
        }

        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode temp = head;
        int i =1;
        while(temp!=null) {
            map.put(i, temp);
            temp = temp.next;
            i++;
        }

        int mid = m+ (n-m)/2;
        while(m <= mid) {
            swap(map,m,n);
            m++;
            n--;
        }


        return head;
    }

    public void swap(HashMap<Integer, ListNode> map, int m, int n) {
        ListNode mNode = map.get(m);

        if(mNode == null) {
            return;
        }

        final int oriMVal = mNode.val;

        ListNode nNode = map.get(n);

        if(nNode== null) {
            return;
        }

        final int oriNVal = nNode.val;

        mNode.val = oriNVal;
        nNode.val = oriMVal;
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
