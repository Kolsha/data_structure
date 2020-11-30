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

Approach 1: Ordered dictionary
Intuition

We're asked to implement the structure which provides the following operations in \mathcal{O}(1)O(1) time :

Get the key / Check if the key exists

Put the key

Delete the first added key

The first two operations in \mathcal{O}(1)O(1) time are provided by the standard hashmap, and the last one - by linked list.

There is a structure called ordered dictionary, it combines behind both hashmap and linked list. In Python this structure is called OrderedDict and in Java LinkedHashMap.

Let's use this structure here.

Implementation
```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 ```
Complexity Analysis

Time complexity : \mathcal{O}(1)O(1) both for put and get since all operations with ordered dictionary : get/in/set/move_to_end/popitem (get/containsKey/put/remove) are done in a constant time.

Space complexity : \mathcal{O}(capacity)O(capacity) since the space is used only for an ordered dictionary with at most capacity + 1 elements.


Approach 2: Hashmap + DoubleLinkedList
Intuition

This Java solution is an extended version of the the article published on the Discuss forum.

The problem can be solved with a hashmap that keeps track of the keys and its values in the double linked list. That results in \mathcal{O}(1)O(1) time for put and get operations and allows to remove the first added node in \mathcal{O}(1)O(1) time as well.

![](https://leetcode.com/problems/lru-cache/Figures/146/structure.png)

One advantage of double linked list is that the node can remove itself without other reference. In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark the boundary, so that we don't need to check the null node during the update.

![](https://leetcode.com/problems/lru-cache/Figures/146/new_node.png)


```java
public class LRUCache {

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
    /**
     * Always add the new node right after head.
     */
    node.prev = head;
    node.next = head.next;

    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node){
    /**
     * Remove an existing node from the linked list.
     */
    DLinkedNode prev = node.prev;
    DLinkedNode next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(DLinkedNode node){
    /**
     * Move certain node in between to the head.
     */
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    /**
     * Pop the current tail.
     */
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    // head.prev = null;

    tail = new DLinkedNode();
    // tail.next = null;

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node == null) return -1;

    // move the accessed node to the head;
    moveToHead(node);

    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);

    if(node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;

      cache.put(key, newNode);
      addNode(newNode);

      ++size;

      if(size > capacity) {
        // pop the tail
        DLinkedNode tail = popTail();
        cache.remove(tail.key);
        --size;
      }
    } else {
      // update the value.
      node.value = value;
      moveToHead(node);
    }
  }
}
```
Complexity Analysis

Time complexity : \mathcal{O}(1)O(1) both for put and get.

Space complexity : \mathcal{O}(capacity)O(capacity) since the space is used only for a hashmap and double linked list with at most capacity + 1 elements.