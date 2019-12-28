### 198. House Robber
https://leetcode.com/problems/house-robber/

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
Solution:
1. Define subproblems<br/>
   Suppose DP<sub>n</sub> will have the maximum money robbed from `n` houses. Since we need to avoid robbing adjacent houses, we will have 2 conditions:
   firstly, if robbed house contains the last house:<br/>
   Money(Condition 1) = DP<sub>n-2</sub> + Money(lastHouse)

   otherwise, robbed house doesn't contain the last house:<br/>
   Money(Condition 2) = DP<sub>n-1</sub>
   
   Get the maximum of condition 1 and condition 2.
2. Write down the recurrence that relates subproblems:<br/>
   DP<sub>n</sub> = $\max$( DP<sub>n-2</sub> + Money(lastHouse), DP<sub>n-1</sub>), n >= 3
   Base cases:
   DP[1] = The first house = nums[0]
   DP[2] = $\max$ (nums[0], nums[1])

   
3. Recognize and solve the base cases

```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);

        for (int i = 3; i < nums.length + 1; i++) {
            // last one is selected
            int lastOneSelected = nums[i - 1] + dp[i - 2];
            // last one is NOT selected
            int lastOneNotSelected = dp[i - 1];
            dp[i] = Math.max(lastOneSelected, lastOneNotSelected);
        }
        return dp[nums.length];
    }
}
```
Since we only need to track the maximum value, we can optimize it to Space(1) complexity:
```java
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        } else if(nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length+1];
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        
        
        for(int i = 3; i < nums.length+1; i++) {
            dp[i] = Math.max(nums[i-1] + first, second);
            first = second;
            second = dp[i];
        }
        return dp[nums.length];
    }
}
```
