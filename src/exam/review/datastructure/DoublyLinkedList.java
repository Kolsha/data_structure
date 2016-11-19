package exam.review.datastructure;
/**
 * Created by shanwu on 16-10-26.
 */
public class DoublyLinkedList {
    private Node mHeader;
    private Node mTrailer;

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst("c");
        list.addFirst("b");
        list.addFirst("a");
        list.printNodesWithHeader();
        list.removeLast();
        list.printNodesWithTrailer();
        list.addAfterValue("b", "c");
        list.printNodesWithHeader();
        list.removeMiddle("b");
        list.printNodesWithHeader();
    }

    public void removeMiddle(String value) {
        Node temp = mHeader;
        while (temp != null) {
            if (temp.value.equals(value)) {
                // removeMiddle middle
                Node nxtNode = temp.next;
                Node preNode = temp.prev;
                if (preNode != null) {
                    preNode.next = nxtNode;
                }

                if (nxtNode != null) {
                    nxtNode.prev = preNode;
                }
                temp.prev = null;
                temp.next = null;
            }
            temp = temp.next;
        }
    }

    public void addAfterValue(String target, String value) {
        Node temp = mHeader;
        while (temp != null) {
            if (temp.value.equals(target)) {
                Node node = new Node();
                node.value = value;
                node.next = temp.next;
                node.prev = temp;
                temp.next = node;
            }
            temp = temp.next;
        }
    }

    private void removeLast() {
        Node lastNode = mTrailer;
        mTrailer = lastNode.prev;
        if (mTrailer != null) {
            mTrailer.next = null;
        }
    }

    private void addFirst(String value) {
        Node node = new Node();
        if (mTrailer == null) {
            mTrailer = node;
        }

        if (mHeader != null) {
            mHeader.prev = node;
        }

        node.value = value;
        node.next = mHeader;
        mHeader = node;
    }

    private void printNodesWithHeader() {
        Node temp = mHeader;
        while (temp != null) {
            if (temp.next != null) {
                System.out.print(temp.value + " -> ");
            } else {
                System.out.print(temp.value + "\n");
            }
            temp = temp.next;
        }
    }

    private void printNodesWithTrailer() {
        Node temp = mTrailer;
        while (temp != null) {
            if (temp.prev != null) {
                System.out.print(temp.value + " -> ");
            } else {
                System.out.print(temp.value + "\n");
            }
            temp = temp.prev;
        }
    }

    public static class Node {
        String value;
        Node prev;
        Node next;
    }
}
