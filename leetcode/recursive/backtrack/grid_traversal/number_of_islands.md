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

Complexity analysis
- Time complexity: O(mn) where m is number of rows and n is number of columns.
- Space complexity: O(mn) where m is number of rows and n is number of columns.

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

Approach 2: BFS
Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Breadth First Search. Put it into a queue and set its value as '0' to mark as visited node. Iteratively search the neighbors of enqueued nodes until the queue becomes empty.

Complexity analysis:
- Time complexity: O(MN) where MM is the number of rows and NN is the number of columns.
- Space complexity: O(min(M, N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
```java
class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int num_islands = 0;

    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == '1') {
          ++num_islands;
          grid[r][c] = '0'; // mark as visited
          Queue<Integer> neighbors = new LinkedList<>();
          neighbors.add(r * nc + c);
          while (!neighbors.isEmpty()) {
            int id = neighbors.remove();
            int row = id / nc;
            int col = id % nc;
            if (row - 1 >= 0 && grid[row-1][col] == '1') {
              neighbors.add((row-1) * nc + col);
              grid[row-1][col] = '0';
            }
            if (row + 1 < nr && grid[row+1][col] == '1') {
              neighbors.add((row+1) * nc + col);
              grid[row+1][col] = '0';
            }
            if (col - 1 >= 0 && grid[row][col-1] == '1') {
              neighbors.add(row * nc + col-1);
              grid[row][col-1] = '0';
            }
            if (col + 1 < nc && grid[row][col+1] == '1') {
              neighbors.add(row * nc + col+1);
              grid[row][col+1] = '0';
            }
          }
        }
      }
    }

    return num_islands;
  }
}
```