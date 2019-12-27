### 53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Solution 1:
Dynamic Programming
Find Subproblem
Assume <b>dp<sub>i</sub></b> which is the subarray ending with the index `i` has the greatest value:

dp<sub>i</sub> = max {dp<sub>i-1</sub> + nums[i], nums[i]}


```java
class Solution {
    public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(A[i] + dp[i - 1] , A[i]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
```