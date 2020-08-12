### 283. Move Zeroes

https://leetcode.com/problems/move-zeroes/

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
```
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
```
Note:

- You must do this in-place without making a copy of the array.
- Minimize the total number of operations.


Solution

Approach 1: 2-pass Counting Sort approach

Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(1)

In Java
```java
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int nonZeroPtr = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i]!=0) {
                nums[nonZeroPtr++] = nums[i];
            }
        }
        for(int i = nonZeroPtr; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

In Python
```python
class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        if len(nums) < 2:
            return

        non_zero_ptr = 0
        for val in nums:
            if val != 0:
                nums[non_zero_ptr] = val
                non_zero_ptr += 1
        
        for i in range(non_zero_ptr, len(nums)):
            nums[i] = 0
```