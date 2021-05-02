### [1. Two Sum](https://leetcode.com/problems/two-sum/)

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

### Solution

#### Approach 1: One Pass HashMap

It turns out we can do it in one-pass. While we iterate and inserting elements into the table, we also look back to check if current element's complement already exists in the table. If it exists, we have found a solution and return immediately.

#### Complexity Analysis
- Time Complexity: O(n)
- Space Complexity: O(n)


Java
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                res[1] = map.get(complement);
                res[0] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
```

Python

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        mapping = dict()
        for i, num in enumerate(nums):
            key = mapping.get(target - num, -1)
            if key != -1:
                return [key, i]
            else:
                mapping[num] = i
        return [-1, -1]
```
