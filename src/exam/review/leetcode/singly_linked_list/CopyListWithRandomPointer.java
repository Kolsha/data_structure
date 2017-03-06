package exam.review.leetcode.singly_linked_list;

import java.util.HashMap;

/**
 * Created by shanwu on 17-3-6.
 */
public class CopyListWithRandomPointer {
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        // init the hashmap
        RandomListNode temp = head;
        while (temp != null) {
            map.put(temp, new RandomListNode(temp.label));
            temp = temp.next;
        }

        temp = head;

        // init each RandomListNode in hash map
        while(temp!=null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }

        return map.get(head);

    }

    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}
