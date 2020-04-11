### 674. Longest Continuous Increasing Subsequence

https://leetcode.com/problems/longest-continuous-increasing-subsequence/

Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
```
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
```
Example 2:
```
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
Note: Length of the array will not exceed 10,000.
```

Solution

Method 1: Dynamic programming

Time Complexity: O(n)

Space Complexity: O(n)

In Java
```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            } else {
                dp[i] = 1;
            }
        }
        return max;
    }
}
```

Method 2: Optimized Dynamic Programming

Time Complexity: O(n)

Space Complexity: 1

In Java
```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dp = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp = dp + 1;
                max = Math.max(max, dp);
            } else {
                dp = 1;
            }
        }
        return max;
    }
}
```

In Python
```python
class Solution(object):
    def findLengthOfLCIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == None or len(nums) == 0:
            return 0
        
        dp, res = 1, 1
        prev = nums[0]
        for num in nums[1:]:
            if num > prev:
                dp += 1
                res = max(res, dp)
            else:
                dp = 1
            prev = num
        return res
```