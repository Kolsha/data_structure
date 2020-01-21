### 120. Triangle
https://leetcode.com/problems/triangle/

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
```
Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Solution:
Method 1: Dynamic Programming - Bottom to Top
```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // bottom up solution
        int row = triangle.size();
        int col = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[col];
        for (int i = 0; i < col; i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```