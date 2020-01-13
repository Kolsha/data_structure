### 170. Two Sum III - Data structure design
https://leetcode.com/problems/two-sum-iii-data-structure-design/

Design and implement a TwoSum class. It should support the following operations: `add` and `find`.

`add` - Add the number to an internal data structure.
`find` - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:
```
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
```
Example 2:
```
add(3); add(1); add(2);
find(3) -> true
find(6) -> false
```
Solution:
```java
class TwoSum {
    HashMap<Integer, Integer> dataHolder = new HashMap<>(); 
    /** Initialize your data structure here. */
    public TwoSum() {
        
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        Integer val = dataHolder.getOrDefault(number, 0);
        dataHolder.put(number, ++val);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry: dataHolder.entrySet()) {
            int num = entry.getKey();
            int anoNum = value - num;
            if(num == anoNum && entry.getValue() > 1) {
                return true;
            } else if(num!= anoNum && dataHolder.containsKey(anoNum)){
                return true;
            }
            
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```