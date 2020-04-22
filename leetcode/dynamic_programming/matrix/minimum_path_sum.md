### 64. Minimum Path Sum
https://leetcode.com/problems/minimum-path-sum/

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

Solution

Optimal method: 1-D Array Dynamic Programming

Time complexity: O(m*n)

Space complexity: O(n)
In Java
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[row];

        dp[0] = grid[0][0];
        for(int i = 1; i < row; i++) {
            dp[i] = dp[i-1] + grid[i][0];
        }

        for(int j = 1; j < col; j++) {
            dp[0] += grid[0][j];
            for(int i = 1; i < row; i++) {
                dp[i] = Math.min(dp[i], dp[i-1]) + grid[i][j];
            }
        }
        return dp[row-1];
    }
}
```





Method 1: Dynamic Programming

Time complexity: O(m*n)

Space complexity: O(m*n)

In Java
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = grid[i][j];
                } else if (i == 0 && j != 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (i != 0 && j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[row - 1][col - 1];
    }
}
```