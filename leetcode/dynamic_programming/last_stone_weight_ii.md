### 1049. Last Stone Weight II

https://leetcode.com/problems/last-stone-weight-ii/

We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

- If x == y, both stones are totally destroyed;
- If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

 

Example 1:
```
Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
``` 

Note:

1. 1 <= stones.length <= 30
2. 1 <= stones[i] <= 100

Solution

Method 1: Dynamic programming

Time complexity:

Space complexity:

Recurrence:
Let's say our total weight of stones is S, and we seperate the stones into 2 piles weights S1 and S2 seperately:
`S = S1 + S2`

The difference of 2 piles stones will be:
`Diff = S1 - S2`
&rarr; `Diff = S - 2 * S2`
&rarr; Find the Diff value by maximizing S2(which value ranges from 0 ~ S/2).

DP[i][j]
    true if some subset from the 1st ~ j'th stone `has a` sum equal to sum i, false otherwise
    i ranges from 0 ~ total stone weight
    j ranges from 1 ~ n

    there're 2 conditions which some subset from the 1st ~jth stone weights i:
    1. We reach the weight already, no extra stone is needed DP[i][j-1] == true
    2. We need jth stone, which means DP[i - jth_Stone_Weight][i-1] == true
&rarr; DP[i][j] = Dp[i][j-1] || DP[i - jth_Stone_Weight][i-1]

DP
```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        // S = S1 + S2
        // Diff = S1 - S2
        // Diff = S - 2 * S2 -> max S2(0 ~ S/2), so Diff will be the smallest
        // Recurrence:
        // dp[S2][Stone Index] = return true if Stone1 ~ StoneN weights S2;
        // 
        int S = 0;
        for(int stone:stones) {
            S+=stone;
        }
        boolean[][] dp = new boolean[S+1][stones.length+1];
        for(int i = 0; i <= stones.length; i++) {
            dp[0][i] = true;
        }
        int S2 = 0;

        for(int i = 1; i <= stones.length; i++) {
            for(int s = 1; s <= S/2; s++) {
                if(dp[s][i-1] || s >= stones[i-1] && dp[s - stones[i-1]][i-1]) {
                    dp[s][i] = true;
                    S2 = Math.max(S2, s);
                }
            }
        }
        return S - 2*S2;
    }
}
```