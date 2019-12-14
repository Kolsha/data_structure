### 256. Paint House
https://leetcode.com/problems/paint-house/

There are a row of n houses, each house can be painted with one of `the three colors: red, blue or green.` The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a `n x 3` cost matrix. For example, `costs[0][0]` is the cost of painting house 0 with color red; `costs[1][2]` is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:<br/>
All costs are positive integers.

Example:
```
Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.
```
**Solution:**<br/>
from: https://leetcode.com/problems/paint-house/discuss/68203/Share-my-very-simple-Java-solution-with-explanation.<br/>
The basic idea is when we have painted the first i houses, and want to paint the i+1 th house, we have 3 choices: paint it either red, or green, or blue. If we choose to paint it red, we have the follow deduction:
```
currPaintRed = min(lastPaintGreen, lastPaintBlue) + costs[i+1][0]
```

```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        // basic state
        // costs for house 0 red color
        int lastPaintRed = costs[0][0];
        int lastPaintBlue = costs[0][1];
        int lastPaintGreen = costs[0][2];

        for (int i = 1; i < costs.length; i++) {
            int currPaintRed = Math.min(lastPaintBlue, lastPaintGreen) + costs[i][0];
            int currPaintBlue = Math.min(lastPaintGreen, lastPaintRed) + costs[i][1];
            int currPaintGreen = Math.min(lastPaintBlue, lastPaintRed) + costs[i][2];
            lastPaintRed = currPaintRed;
            lastPaintBlue = currPaintBlue;
            lastPaintGreen = currPaintGreen;
        }
        return Math.min(lastPaintRed, Math.min(lastPaintGreen, lastPaintBlue));
    }
}
```