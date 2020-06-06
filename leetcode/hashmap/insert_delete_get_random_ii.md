### 381. Insert Delete GetRandom O(1) - Duplicates allowed

https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
1. insert(val): Inserts an item val to the collection.
2. remove(val): Removes an item val from the collection if present.
3. getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:
```
// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
```

Solution

Method 1: HashMap + ArrayList + LinkedHashSet

```java
class RandomizedCollection {
    private HashMap<Integer, LinkedHashSet<Integer>> map = new HashMap<>();
    private ArrayList<Integer> nums = new ArrayList<>();
    private Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if(!contain) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(nums.size());
        nums.add(val);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean contain = map.containsKey(val);
        if(!contain) {
            return false;
        }
        
        int index = map.get(val).iterator().next();
        // we will have to remove this index first, otherwise, we might break the order of LinkedHashSet
        map.get(val).remove(index);

        int lastIndex = nums.size()-1;
        if(index < lastIndex) {
            // swap, update nums and map
            int lastOne = nums.get(lastIndex);
            nums.set(index, lastOne);
            map.get(lastOne).add(index);
            map.get(lastOne).remove(lastIndex);
        }

        nums.remove(lastIndex);

        if(map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```