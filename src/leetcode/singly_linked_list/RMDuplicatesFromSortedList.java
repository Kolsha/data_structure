package leetcode.singly_linked_list;
import leetcode.singly_linked_list.ReverseLinkedList.ListNode;
/**
 * Created by shanwu on 16-12-30.
 */
public class RMDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        ListNode next = null;

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
