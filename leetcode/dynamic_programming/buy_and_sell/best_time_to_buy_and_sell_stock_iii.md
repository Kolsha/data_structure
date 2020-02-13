### 123. Best Time to Buy and Sell Stock III

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:
```
Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
```
Example 2:
```
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
```
Example 3:
```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

Solution

```java
class Solution {

    // For k transactions, on i-th day,
    // if we don't trade then the profit is same as previous day dp[k][i-1];
    // if we bought the share on j-th day where j=[0..i-1],
    // and sell the share on i-th day then the profit is prices[i] - prices[j] +
    // dp[k-1, j-1] .
    // Actually j can be i as well. When j is i, the one more extra item prices[i] -
    // prices[j] + dp[k-1, j] = dp[k-1, i] looks like we just lose one chance of
    // transaction.
    // dp[k][i] = max(dp[k][i-1], prices[i] - prices[j] + dp[k-1][j-1]), j=[0..i-1]
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[3][prices.length];
        for (int k = 1; k < 3; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                for (int j = 1; j <= i; j++) {
                    min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
                }
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[2][prices.length - 1];
    }
}
```

After optimization:
//todo

```java
```