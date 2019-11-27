package exam.review.leetcode.singly_linked_list;

/**
 * Created by shanwu on 16-12-30.
 * PC: 1
 */
public class RMDuplicatesFromSortedList {
    public ReverseLinkedList.ListNode deleteDuplicates(ReverseLinkedList.ListNode head) {
        ReverseLinkedList.ListNode temp = head;
        ReverseLinkedList.ListNode next = null;

        while(temp!=null) {
            next = temp.next;
            while(next!=null && temp.val == next.val) {
                temp.next = next.next;
                next = temp.next;
            }
            temp = next;
        }
        return head;
    }
}
