### 309. Best Time to Buy and Sell Stock with Cooldown
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:
```
Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
```

Solution

[Method 1: FSM Dynamic Programming](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking))

// s0: initial state
// s1: state after state s0 buy the stock
// s2: state after state s1 sell the stock
```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] s0 = new int[prices.length];
        int[] s1 = new int[prices.length];
        int[] s2 = new int[prices.length];

        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            // rest[i-1] - share
            s0[i] = Math.max(s2[i - 1], s0[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - price);
            s2[i] = s1[i - 1] + price;
        }
        return Math.max(s2[prices.length - 1], s0[prices.length - 1]);
    }
}
```
Can be optimized to:
```java
```