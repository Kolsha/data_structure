package exam.review.datastructure;
/**
 * Created by shanwu on 16-10-26.
 */
public class SinglyLinkedList {
    private Node mFirst = null;
    private Node mTail = null;

    public SinglyLinkedList() {
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addNodeToLast("a");
        list.addNodeToLast("b");
        list.addNodeToLast("c");
        list.printNodes();
        System.out.println(list.getNthToLast(1).value);

    }

    private void addNodeToLast(final String s) {
        Node node = new Node();
        node.next = null;
        node.value = s;

        if (mFirst == null) {
            mFirst = node;
        }

        if (mTail == null) {
            mTail = node;
        } else {
            mTail.next = node;
            mTail = node;
        }
    }

    public Node getNthToLast(final int n) {
        if (mFirst == null || n < 1) {
            return null;
        }

        Node ptr1 = mFirst;
        Node ptr2 = mFirst;

        for (int i = 0; i < n - 1; i++) {
            if (ptr2 == null) {
                return null;
            }
            ptr2 = ptr2.next;
        }

        while (ptr2.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    public void printNodes() {
        Node temp = mFirst;
        while (temp != null) {
            if (temp.next != null) {
                System.out.print(temp.value + " -> ");
            } else {
                System.out.print(temp.value + "\n");
            }
            temp = temp.next;
        }
    }

    public static class Node {
        String value;
        Node next;
    }
}
