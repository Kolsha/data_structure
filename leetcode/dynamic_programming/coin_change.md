### 322. Coin Change
https://leetcode.com/problems/coin-change/

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
```
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
```
Example 2:
```
Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
```

Solution

// todo handle if Amount=Integer.MAX_VALUE,coins=[1],the code would fail


```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] = dp[i-1] + dp[i-2] + dp[i-5]

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }
}
```

Approach #2 (Dynamic programming - Top down) [Accepted]
Intuition

Could we improve the exponential solution above? Definitely! The problem could be solved with polynomial time using Dynamic programming technique. First, let's define:

F(S)F(S) - minimum number of coins needed to make change for amount SS using coin denominations [{c_0\ldots c_{n-1}}][c 
0
​	
 …c 
n−1
​	
 ]

We note that this problem has an optimal substructure property, which is the key piece in solving any Dynamic Programming problems. In other words, the optimal solution can be constructed from optimal solutions of its subproblems. How to split the problem into subproblems? Let's assume that we know F(S)F(S) where some change val_1, val_2, \ldotsval 
1
​	
 ,val 
2
​	
 ,… for SS which is optimal and the last coin's denomination is CC. Then the following equation should be true because of optimal substructure of the problem:

F(S) = F(S - C) + 1F(S)=F(S−C)+1

But we don't know which is the denomination of the last coin CC. We compute F(S - c_i)F(S−c 
i
​	
 ) for each possible denomination c_0, c_1, c_2 \ldots c_{n -1}c 
0
​	
 ,c 
1
​	
 ,c 
2
​	
 …c 
n−1
​	
  and choose the minimum among them. The following recurrence relation holds:

F(S) = \min_{i=0 ... n-1} { F(S - c_i) } + 1 \\ \text{subject to} \ S-c_i \geq 0 \\F(S)=min 
i=0...n−1
​	
 F(S−c 
i
​	
 )+1
subject to S−c 
i
​	
 ≥0

F(S) = 0 , \text{when} S = 0 \\ F(S) = -1 , \text{when} n = 0F(S)=0,whenS=0
F(S)=−1,whenn=0

![](https://leetcode.com/media/original_images/322_coin_change_tree.png)

In the recursion tree above, we could see that a lot of subproblems were calculated multiple times. For example the problem F(1)F(1) was calculated 1313 times. Therefore we should cache the solutions to the subproblems in a table and access them in constant time when necessary

Algorithm

The idea of the algorithm is to build the solution of the problem from top to bottom. It applies the idea described above. It use backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable solution. Тhis happens when we try to make a change of a coin with a value greater than the amount SS. To improve time complexity we should store the solutions of the already calculated subproblems in a table.

```java
public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) return 0;
    return coinChange(coins, amount, new int[amount]);
  }

  private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) return -1;
    if (rem == 0) return 0;
    if (count[rem - 1] != 0) return count[rem - 1];
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChange(coins, rem - coin, count);
      if (res >= 0 && res < min)
        min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }
}
```

Complexity Analysis

Time complexity : O(S*n)O(S∗n). where S is the amount, n is denomination count. In the worst case the recursive tree of the algorithm has height of SS and the algorithm solves only SS subproblems because it caches precalculated solutions in a table. Each subproblem is computed with nn iterations, one by coin denomination. Therefore there is O(S*n)O(S∗n) time complexity.

Space complexity : O(S)O(S), where SS is the amount to change We use extra space for the memoization table.

Approach #3 (Dynamic programming - Bottom up) [Accepted]
Algorithm

For the iterative solution, we think in bottom-up manner. Before calculating F(i)F(i), we have to compute all minimum counts for amounts up to ii. On each iteration ii of the algorithm F(i)F(i) is computed as \min_{j=0 \ldots n-1}{F(i -c_j)} + 1min 
j=0…n−1
​	
 F(i−c 
j
​	
 )+1

 ![](https://leetcode.com/media/original_images/322_coin_change_table.png)

 In the example above you can see that:

\begin{aligned} F(3) &= \min\{{F(3- c_1), F(3-c_2), F(3-c_3)}\} + 1 \\ &= \min\{{F(3- 1), F(3-2), F(3-3)}\} + 1 \\ &= \min\{{F(2), F(1), F(0)}\} + 1 \\ &= \min\{{1, 1, 0}\} + 1 \\ &= 1 \end{aligned} 
F(3)
​	
  
=min{F(3−c 
1
​	
 ),F(3−c 
2
​	
 ),F(3−c 
3
​	
 )}+1
=min{F(3−1),F(3−2),F(3−3)}+1
=min{F(2),F(1),F(0)}+1
=min{1,1,0}+1
=1
​	
 
```java
public class Solution {
  public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }
}
```

Complexity Analysis

Time complexity : O(S*n)O(S∗n). On each step the algorithm finds the next F(i)F(i) in nn iterations, where 1\leq i \leq S1≤i≤S. Therefore in total the iterations are S*nS∗n.
Space complexity : O(S)O(S). We use extra space for the memoization table.