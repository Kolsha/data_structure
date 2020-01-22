### 312. Burst Balloons

https://leetcode.com/problems/burst-balloons/

Given `n` balloons, indexed from `0` to `n-1`. Each balloon is painted with a number on it represented by array `nums`. You are asked to burst all the balloons. If the you burst balloon i you will get `nums[left] * nums[i] * nums[right]` coins. Here `left` and `right` are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

- You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
- 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:
```
Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

```
Solution
From: https://www.youtube.com/watch?v=z3hu2Be92UA
```java
class Solution {

    public int maxCoins(int[] nums) {
        // why do we need padding? b/c dp[i][j] is the solution for max coins from i ~ j
        // we'll need i-1, and j+1 to generalize the Recurrence function
        // which is dp[i][j] = Math.max(dp[i][j],
        // dp[i][k-1]+ numsWithPadding[i-1] * numsWithPadding[k] *
        // numsWithPadding[j+1]+dp[k+1][j]);
        int[] numsWithPadding = new int[nums.length + 2];
        numsWithPadding[0] = numsWithPadding[numsWithPadding.length - 1] = 1;
        int n = 1;
        for (int val : nums) {
            numsWithPadding[n++] = val;
        }

        // dp i ~j
        int[][] dp = new int[numsWithPadding.length][numsWithPadding.length];
        for (int len = 1; len <= nums.length; len++) {
            for (int i = 1; i + len - 1 <= nums.length; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1]
                            + numsWithPadding[i - 1] * numsWithPadding[k] * numsWithPadding[j + 1] + dp[k + 1][j]);
                }
            }
        }

        return dp[1][nums.length];
    }
}
```