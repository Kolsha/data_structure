### 146. LRU Cache

https://leetcode.com/problems/lru-cache/

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
```
LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

Solution

Method 1: HashMap + Doubly linkedlist

```java
class Node {
  int value;
  int key;
  Node prev;
  Node next;
  public Node() {}

  public Node(int key, int value) {
    this.key = key;
    this.value = value;
    this.prev = null;
    this.next = null;
  }
}

class DoublyLinkedList {
  Node head;
  Node tail;
  
  public DoublyLinkedList() {
    head = new Node();
    tail = new Node();
    this.head.next = this.tail;
    this.tail.prev = head;
  }
  
  public void insertHead(Node n) {
    n.prev = head;
    n.next = head.next;
    head.next.prev = n;
    head.next = n;    
  }
  
  public void remove(Node n) {
    n.prev.next = n.next;
    n.next.prev = n.prev;
  }
  
  public int removeTail() {
    Node n = tail.prev;
    int key = n.key;
    remove(n);
    
    return key;
  }
}

class LRUCache {
  Map<Integer,Node> cache;
  DoublyLinkedList list;
  int capacity;
  
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>();
    this.list = new DoublyLinkedList();
  }
  
  public int get(int key) {
    if (!cache.containsKey(key)) 
        return -1;    
    update(key, cache.get(key));
      
    return cache.get(key).value;
  }
  
  public void put(int key, int value) {
    Node n = new Node(key, value);
      
    if (cache.containsKey(key))
      list.remove(cache.get(key));
    else if (cache.size() >= capacity) {
        int k = list.removeTail();
        cache.remove(k);
    }
        
    list.insertHead(n);
    cache.put(key, n);
  }
  
  private void update(int key, Node n) {    
    list.remove(n);
    list.insertHead(n);
    cache.put(key, n);
  }
}
```