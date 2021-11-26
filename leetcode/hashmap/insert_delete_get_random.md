### [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)


Design a data structure that supports all following operations in average O(1) time.

- insert(val): Inserts an item val to the set if not already present.
- remove(val): Removes an item val from the set if present.
- getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Example:
```
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
```

Follow-up question: How do you modify your code to allow duplicated number?

##### Solution

##### Approach 1: HashMap + ArrayList
##### Complexity analysis
- Time complexity: $O(1)$
- Space complexity: $O(N)$

```java
public class RandomizedSet {
    private ArrayList<Integer> nums;
    private HashMap<Integer, Integer> locs;
    private Random rand = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if ( contain ) {
            return false;
        }
        locs.put( val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) {
            return false;
        }
        int loc = locs.get(val);
        if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
            int lastone = nums.get(nums.size() - 1 );
            nums.set( loc , lastone );
            locs.put(lastone, loc);
        }
        locs.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get( rand.nextInt(nums.size()) );
    }
}
```
Solution
Overview
We're asked to implement the structure which provides the following operations in average \mathcal{O}(1)O(1) time:

Insert

Delete

GetRandom

First of all - why this weird combination? The structure looks quite theoretical, but it's widely used in popular statistical algorithms like Markov chain Monte Carlo and Metropolis–Hastings algorithm. These algorithms are for sampling from a probability distribution when it's difficult to compute the distribution itself.

Let's figure out how to implement such a structure. Starting from the Insert, we immediately have two good candidates with \mathcal{O}(1)O(1) average insert time:

Hashmap (or Hashset, the implementation is very similar): Java HashMap / Python dictionary

Array List: Java ArrayList / Python list

Let's consider them one by one.

Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.

The idea of GetRandom is to choose a random index and then to retrieve an element with that index. There is no indexes in hashmap, and hence to get true random value, one has first to convert hashmap keys in a list, that would take linear time. The solution here is to build a list of keys aside and to use this list to compute GetRandom in constant time.

Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.

To delete a value at arbitrary index takes linear time. The solution here is to always delete the last value:

Swap the element to delete with the last one.

Pop the last element out.

For that, one has to compute an index of each element in constant time, and hence needs a hashmap which stores element -> its index dictionary.

Both ways converge into the same combination of data structures:

Hashmap element -> its index.

Array List of elements.

![](https://leetcode.com/problems/insert-delete-getrandom-o1/Figures/380/structure2.png)

Approach 1: HashMap + ArrayList
Insert

Add value -> its index into dictionary, average \mathcal{O}(1)O(1) time.

Append value to array list, average \mathcal{O}(1)O(1) time as well.

![](https://leetcode.com/problems/insert-delete-getrandom-o1/Figures/380/isert.png)

```java
/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
public boolean insert(int val) {
  if (dict.containsKey(val)) return false;
    
  dict.put(val, list.size());
  list.add(list.size(), val);
  return true;
}
```

Delete

Retrieve an index of element to delete from the hashmap.

Move the last element to the place of the element to delete, \mathcal{O}(1)O(1) time.

Pop the last element out, \mathcal{O}(1)O(1) time.

![](https://leetcode.com/problems/insert-delete-getrandom-o1/Figures/380/delete.png)

```java
/** Removes a value from the set. Returns true if the set contained the specified element. */
public boolean remove(int val) {
  if (! dict.containsKey(val)) return false;

  // move the last element to the place idx of the element to delete
  int lastElement = list.get(list.size() - 1);
  int idx = dict.get(val);
  list.set(idx, lastElement);
  dict.put(lastElement, idx);
  // delete the last element
  list.remove(list.size() - 1);
  dict.remove(val);
  return true;
}
```

GetRandom

GetRandom could be implemented in \mathcal{O}(1)O(1) time with the help of standard random.choice in Python and Random object in Java.

```java
/** Get a random element from the set. */
public int getRandom() {
  return list.get(rand.nextInt(list.size()));
}
```


```java
class RandomizedSet {
  Map<Integer, Integer> dict;
  List<Integer> list;
  Random rand = new Random();

  /** Initialize your data structure here. */
  public RandomizedSet() {
    dict = new HashMap();
    list = new ArrayList();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (dict.containsKey(val)) return false;

    dict.put(val, list.size());
    list.add(list.size(), val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (! dict.containsKey(val)) return false;

    // move the last element to the place idx of the element to delete
    int lastElement = list.get(list.size() - 1);
    int idx = dict.get(val);
    list.set(idx, lastElement);
    dict.put(lastElement, idx);
    // delete the last element
    list.remove(list.size() - 1);
    dict.remove(val);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}
```

Complexity Analysis

Time complexity. GetRandom is always \mathcal{O}(1)O(1). Insert and Delete both have \mathcal{O}(1)O(1) average time complexity, and \mathcal{O}(N)O(N) in the worst-case scenario when the operation exceeds the capacity of currently allocated array/hashmap and invokes space reallocation.

Space complexity: \mathcal{O}(N)O(N), to store N elements.

