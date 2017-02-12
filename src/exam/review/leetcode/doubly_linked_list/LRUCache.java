package exam.review.leetcode.doubly_linked_list;

import java.util.HashMap;

public class LRUCache {
    private int mCapacity;
    private int mCurrSize;

    private HashMap<Integer, Node> mMap = null;
    private Node mHead = null;
    private Node mTail = null;

    public LRUCache(int cap) {
        mCapacity = cap;
        mCurrSize = 0;
        mMap = new HashMap<>();
        mHead = new Node(0, 0);
        mTail = new Node(0, 0);
        mHead.next = mTail;
        mTail.next = null;
        mTail.pre = mHead;
    }

    public void addToFront(Node node) {
        mHead.next.pre = node;
        node.next = mHead.next;
        node.pre = mHead;
        mHead.pre = null;
        mHead.next = node;
    }

    public void remove(Node node) {
        Node preNode = node.pre;
        Node nxtNode = node.next;
        if (preNode != null) preNode.next = nxtNode;
        if (nxtNode != null) nxtNode.pre = preNode;
    }

    public int get(int key) {
        Node node = mMap.get(key);
        if (node == null) {
            return -1;
        }

        remove(node);
        addToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (mMap.get(key) != null) {
            Node node = mMap.get(key);
            node.value = value;
            remove(node);
            addToFront(node);
        } else {
            Node node = new Node(key, value);
            mMap.put(key, node);

            if (mCurrSize < mCapacity) {
                mCurrSize++;
            } else {
                Node lastNode = mTail.pre;
                remove(lastNode);
                mMap.remove(lastNode.key);
            }
            addToFront(node);

        }

    }

    public class Node {
        int key;
        int value;

        Node next;
        Node pre;

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
}
