### 300. Longest Increasing Subsequence

https://leetcode.com/problems/longest-increasing-subsequence/

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```
Note:

- There may be more than one LIS combination, it is only necessary for you to return the length.
- Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Solution

Method 1: Dynamic programming

Time complexity: O(n^2)

Space complexity: O(n)

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

In Python

```python
class Solution(object):
    def lengthOfLIS(self, nums):
        if len(nums) == 0:
            return 0
        """
        :type nums: List[int]
        :rtype: int
        """
        dp = [1] * len(nums)
        res = 1;
        for i in range(1, len(nums)):
            for j in range(0, i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[j]+1, dp[i])
            res = max(res, dp[i])
        return res
```

Method 2: Dynamic Programming + Binary Search

https://leetcode.com/problems/longest-increasing-subsequence/discuss/74825/Short-Java-solution-using-DP-O(n-log-n)

Approach 4: Dynamic Programming with Binary Search
Algorithm

In this approach, we scan the array from left to right. We also make use of a dp array initialized with all 0's. This dp array is meant to store the increasing subsequence formed by including the currently encountered element. While traversing the numsnums array, we keep on filling the dp array with the elements encountered so far. For the element corresponding to the j<sup>th</sup> index (nums[j]), we determine its correct position in the dpdp array(say i<sup>th</sup>index) by making use of Binary Search(which can be used since the dp array is storing increasing subsequence) and also insert it at the correct position. An important point to be noted is that for Binary Search, we consider only that portion of the dpdp array in which we have made the updates by inserting some elements at their correct positions(which remains always sorted). Thus, only the elements upto the i<sup>th</sup> index in the dp array can determine the position of the current element in it. Since, the element enters its correct position(ii) in an ascending order in the dp array, the subsequence formed so far in it is surely an increasing subsequence. Whenever this position index ii becomes equal to the length of the LIS formed so far(lenlen), it means, we need to update the lenlen as len = len + 1len=len+1.

Note: dpdp array does not result in longest increasing subsequence, but length of dpdp array will give you length of LIS.

Consider the example:

input: [0, 8, 4, 12, 2]

dp: [0]

dp: [0, 8]

dp: [0, 4]

dp: [0, 4, 12]

dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dp array results in length of Longest Increasing Subsequence.

Complexity analysis
- Time complexity : O(n\log n)O(nlogn). Binary search takes `logn` time and it is called `n` times.

- Space complexity : O(n). `dp` array of size `n` is used.

```java
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
```