### [198. House Robber](https://leetcode.com/problems/house-robber/)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night.</b>

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight <b>without alerting the police.</b>

Example 1:
```
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
```
Example 2:
```
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
```
##### Solution

##### Approach 1: Dynamic programming without optimization

Define subproblems
`DP[i]` 为到位置为 `i` 的抢匪可抢最大值
`DP[i] = Math.max(目前这家的钱 + DP[i-2], DP[i-1])`

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(n)

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];
        dp[0] = nums[0];
        if(n == 1) {
            return nums[0];
        }
        
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        
        return dp[n-1];
    }
}
```

##### Approach 2: Dynamic programming after optimization
Since we only need to track the maximum value, we can optimize it to O(1) space complexity.

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public int rob(int[] nums) {
        int curMax = 0;
        int preMax = 0;
        for(int num: nums) {
            int temp = curMax;
            curMax = Math.max(num+preMax, curMax);
            preMax = temp;
        }

        return curMax;
    }
}
```
