### 136. Single Number

https://leetcode.com/problems/single-number/

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
```
Input: [2,2,1]
Output: 1
```
Example 2:
```
Input: [4,1,2,1,2]
Output: 4
```

Solution

Method 1: [XOR operation](https://www.baeldung.com/java-xor-operator)

Time complexity: O(n)

Space complexity: O(1)

In Java
```java
class Solution {
    public int singleNumber(int[] nums) {
        int target = 0;
        for(int val : nums) {
            // xor
            target ^= val;
        }
        return target;
    }
}
```

In Python
```python
class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        res = 0
        for val in nums:
            res ^= val
        return res
```