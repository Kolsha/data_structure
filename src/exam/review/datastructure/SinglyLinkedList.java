package exam.review.datastructure;

/**
 * Created by shanwu on 16-10-26.
 */
public class SinglyLinkedList<T> {
    private Node mFirst = null;
    private Node mTail = null;
    protected int size = 0;

    public SinglyLinkedList() {
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addNodeToLast("a");
        list.addNodeToLast("b");
        list.addNodeToLast("c");
        list.addNodeToLast("d");
        list.addNodeToLast("a");
        list.printNodes();
        System.out.println(list.getNthToLast(1).value);
        list.remove(new Node("a"));
        list.printNodes();
        list.remove(new Node("a"));
        list.printNodes();
        list.remove(new Node("c"));
        list.printNodes();

    }

    public void addNodeToLast(final T s) {
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
        size++;
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

    public boolean isFirst(Node<T> n) {
        if (isEmpty()) return false;
        return mFirst.value.equals(n.value);
    }

    public boolean isLast(Node<T> n) {
        if (isEmpty()) return false;
        return mTail.value.equals(n.value);
    }

    public Node<T> remove(Node<T> ptr) {
        if (isFirst(ptr)) {
            return removeFirst();
        } else if (isLast(ptr)) {
            return removeLast();
        } else {
            return removeMiddle(ptr);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> removeMiddle(Node<T> target) {
        if (target == null) {
            return null;
        }

        Node<T> temp = mFirst;
        Node<T> prevTemp = temp;
        while (temp != null) {
            if (temp.value.equals(target.value)) {
                prevTemp.next = temp.next;
                Node<T> result = temp;
                result.next = null;
                return result;
            }
            prevTemp = temp;
            temp = temp.next;
        }
        return null;
    }

    public Node<T> removeLast() {
        Node result = mTail;
        size--;
        if (!isEmpty()) {
            Node temp = mFirst;
            int i = 0;
            while (i < size - 1) {
                temp = temp.next;
                i++;
            }
            mTail = temp;
            mTail.next = null;
        }

        if (result != null) {
            result.next = null;
        }
        return result;
    }

    public Node<T> removeFirst() {
        Node result = mFirst;
        if (mFirst != null) {
            mFirst = mFirst.next;
            size--;
        }

        if (result != null) {
            result.next = null;
        }
        return result;
    }

    public void addArrayToList(final T[] array) {
        for (int i = 0; i < array.length; i++) {
            addNodeToLast(array[i]);
        }
    }

    public Node<T> peek() {
        return mFirst;
    }

    public static class Node<T> {
        T value;
        Node next;

        public Node() {
        }

        public Node(T a) {
            value = a;
        }
    }
}
