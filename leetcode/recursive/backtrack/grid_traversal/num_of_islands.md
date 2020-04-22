### 200. Number of Islands
https://leetcode.com/problems/number-of-islands/

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
```
Input:
11110
11010
11000
00000

Output: 1
```
Example 2:
```
Input:
11000
11000
00100
00011

Output: 3
```

Solution

Method 1: DFS-Marking

Time complexity: O(mn) where m is number of rows and n is number of columns.

Space complexity: O(mn) where m is number of rows and n is number of columns.

```java
class Solution {

    public int numIslands(char[][] grid) {
        int count = 0;
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j, row, col);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j, int row, int col) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j, row, col);
        DFSMarking(grid, i - 1, j, row, col);
        DFSMarking(grid, i, j + 1, row, col);
        DFSMarking(grid, i, j - 1, row, col);
    }
}
```