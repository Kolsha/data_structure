### [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in **any order**.

 

Example 1:
```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```
Example 2:
```
Input: nums = [1], k = 1
Output: [1]
``` 

Constraints:

- $1 <= nums.length <= 10^5$
- k is in the range [1, the number of unique elements in the array].
- It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

##### Solution

##### Approach 1: Heap + HashMap

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            queue.add(entry);
        }
        
        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            int value = queue.poll().getKey();
            res[i] = value;
        }
        return res;
    }
}
```