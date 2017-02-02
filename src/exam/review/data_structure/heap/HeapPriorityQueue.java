package exam.review.data_structure.heap;


import exam.review.data_structure.BTNodeADT;
import exam.review.data_structure.TreeNode;
import exam.review.data_structure.misc.DefaultComparator;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by shanwu on 17-2-2.
 */
public class HeapPriorityQueue<K, V> {
    private AListCompleteBTree<Entry<K, V>> mHeap;
    private Comparator<K> mComp;

    public HeapPriorityQueue() {
        mHeap = new AListCompleteBTree<>();
        mComp = new DefaultComparator();
    }

    public HeapPriorityQueue(Comparator<K> cmp) {
        mComp = cmp;
    }

    public int size() {
        return mHeap.size();
    }

    public boolean isEmpty() {
        return mHeap.isEmpty();
    }

    public Entry<K, V> min() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue is empty");
        }
        return mHeap.getRoot().getElement();
    }

    public Entry<K, V> insert(K k, V v) {
        Entry<K, V> newEntry = new MyEntry<K, V>(k, v);
        upHeapBubbling(mHeap.add(newEntry));
        return newEntry;
    }

    public Entry<K, V> removeMin() {
        if (isEmpty()) {
            throw new RuntimeException("priority queue is empty");
        }

        if (size() == 1) {
            return mHeap.remove();
        }

        Entry<K, V> min = mHeap.root().getElement();
        mHeap.replace(mHeap.root(), mHeap.remove());
        downHeapBubbling(mHeap.root());
        return min;
    }

    // todo
    private void downHeapBubbling(TreeNode<Entry<K, V>> root) {

    }

    // todo
    private void upHeapBubbling(BTNodeADT<Entry<K, V>> add) {

    }


    private static class MyEntry<K, V> implements Entry<K, V> {
        private K mKey;
        private V mValue;

        MyEntry(K k, V v) {
            mKey = k;
            mValue = v;
        }

        @Override
        public K getKey() {
            return mKey;
        }

        @Override
        public V getValue() {
            return mValue;
        }

        @Override
        public V setValue(V value) {
            mValue = value;
            return mValue;
        }
    }
}