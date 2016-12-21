package leetcode.doubly_linked_list;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/lru-cache/
 * Created by shanwu on 16-12-19.
 */
// FIXME: 16-12-20 understand it and solve this problem!
public class LRUCache {
    private int mCacheSize;
    private LinkedList<CacheNode> mList = new LinkedList();
    private HashMap<Integer, CacheNode> mMap = new HashMap<>();

    public LRUCache(int capacity) {
        mCacheSize = capacity;

    }

    public int get(int key) {
        if (key > mCacheSize) {
            return -1;
        }

        // swap linked list position
        CacheNode node = mMap.get(key);
        CacheNode oldFirst = mList.getFirst();
        mList.set(key, oldFirst);
        mList.set(0, node);

        // update map
        mMap.put(key, oldFirst);
        mMap.put(oldFirst.key, node);

        return node.value;
    }

    public void set(int key, int value) {
        // update
        if (mMap.containsKey(key)) {
            CacheNode last =  mMap.replace(key, new CacheNode(key, value));
            for(CacheNode node: mList) {
                if(node.equals(last)) {
                    mList.set(node.key,mMap.get(key));
                }
            }
        } else {
            // insert
            CacheNode node = new CacheNode(key, value);
            mList.add(0, node);
            mMap.put(key, node);
        }

        if (mList.size() == mCacheSize) {
            CacheNode last = mList.removeLast();
            mMap.remove(last.key);
        }
    }

    public static class CacheNode {
        int key;
        int value;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);
        cache.set(1,10);
        cache.set(2,9);
        cache.get(1);
    }
}
