### 361. Bomb Enemy

https://leetcode.com/problems/bomb-enemy/

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:
```
Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
```

Solution

Method 1: Intuitive way
```java
class Solution {
    private int count = 0;

    public int maxKilledEnemies(char[][] grid) {
        ArrayList<Point> list = new ArrayList<>();
        // find 0s
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    list.add(new Point(i, j));
                }
            }
        }

        int max = 0;
        // find the max enemy you can bomb
        for (Point target : list) {
            int temp = getBombedCount(grid, target);
            max = Math.max(max, temp);
        }
        return max;
    }

    private int getBombedCount(char[][] grid, Point target) {
        int result = 0;
        int r = target.row;
        int c = target.col;

        // go up
        for (int i = r - 1; i >= 0; i--) {
            if (grid[i][c] == 'E') {
                result++;
            }

            if (grid[i][c] == 'W') {
                break;
            }
        }

        // go down
        for (int i = r; i < grid.length; i++) {
            if (grid[i][c] == 'E') {
                result++;
            }

            if (grid[i][c] == 'W') {
                break;
            }
        }

        // go left
        for (int j = c - 1; j >= 0; j--) {
            if (grid[r][j] == 'E') {
                result++;
            }

            if (grid[r][j] == 'W') {
                break;
            }
        }

        // go down
        for (int j = c; j < grid[0].length; j++) {
            if (grid[r][j] == 'E') {
                result++;
            }

            if (grid[r][j] == 'W') {
                break;
            }
        }
        return result;
    }

    private static class Point {
        int row = -1;
        int col = -1;

        public Point(int r, int c) {
            row = r;
            col = c;
        }
    }
}
```

Method 2: Dynamic Programming
