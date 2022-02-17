### [1219. Path with Maximum Gold](https://leetcode.com/problems/path-with-maximum-gold/)


In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 

Example 1:
```
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
```
Example 2:
```
Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
``` 

Constraints:

- 1 <= grid.length, grid[i].length <= 15
- 0 <= grid[i][j] <= 100
- There are at most `25` cells containing gold.


Solution

Method 1: DFS + HashSet to memorize the visited grid

Time complexity:

Space complexity:

In Java
```java
class Solution {
    private int maxMoney = 0;
    public int getMaximumGold(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                // find the start point
                    dfs(grid, i, j, row, col, new HashSet<>(), 0);
            }
        }
        return maxMoney;
    }
 
    private void dfs(int[][] grid, int strRow, int strCol, int maxRow, int maxCol, HashSet<Integer> set, int sum) {
        if(strRow < 0 || strRow >= maxRow || strCol < 0 || strCol >= maxCol || grid[strRow][strCol] == 0) {
            if(sum == 370) {
                System.out.println(set);
            }
            return;
        }
        // make a signature to represent row and col
        int sig = strRow * maxCol + strCol;
        if(!set.add(sig)) {
            return;
        }
        
        sum += grid[strRow][strCol];
        maxMoney = Math.max(maxMoney, sum);
        
        // move 4 different directions
        int[][] dirs = new int[][]{
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
        };
        
        for(int i = 0; i < dirs.length; i++) {
            int nextRow = strRow + dirs[i][0];
            int nextCol = strCol + dirs[i][1];
            dfs(grid, nextRow, nextCol, maxRow, maxCol, set, sum);
        }
        set.remove(sig);
    }
}
```
