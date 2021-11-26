### 52. N-Queens II

https://leetcode.com/problems/n-queens-ii/

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

![](https://assets.leetcode.com/uploads/2018/10/12/8-queens.png)

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Solution

Method 1: Same approach for N Queens
```java
class Solution {
    public int totalNQueens(int n) {
        // init a nxn chessboard
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        ArrayList<List<String>> res = new ArrayList<>();

        dfsHelper(board, 0, res);
        return res.size();
    }

    private void dfsHelper(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        int row = board.length;
        for (int i = 0; i < row; i++) {
            if (valid(board, i, colIndex)) {
                // place queen
                board[i][colIndex] = 'Q';
                dfsHelper(board, colIndex + 1, res);
                // restore the board
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean valid(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < col; j++) {
                // Because you place the queen every time on a different column ( the recursion
                // call adds 1 to columnIdx everytime), so you don't really need to check for
                // column
                // (Math.abs(row - i) == Math.abs(col - j) -> (row + j == col + i) || (row+ col
                // == i + j)) same thing
                if (board[i][j] == 'Q' && ((row == i) || Math.abs(row - i) == Math.abs(col - j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> construct(char[][] board) {
        ArrayList<String> res = new ArrayList<>();
        int row = board.length;
        for (int i = 0; i < row; i++) {
            res.add(new String(board[i]));
        }
        return res;
    }
}
```

[Method 2](https://leetcode.wang/leetCode-52-N-QueensII.html)
![](https://windliang.oss-cn-beijing.aliyuncs.com/52_2.jpg)

```java
class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        // int r1 + c1 =
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        boolean[] col = new boolean[n];

        // start back tracking
        dfsHelper(n, 0, diag1, diag2, col);

        return count;
    }

    private void dfsHelper(int max, int row, boolean[] diag1, boolean[] diag2, boolean[] col) {
        if (max == row) {
            count++;
            return;
        }

        for (int j = 0; j < max; j++) {
            // row + col
            int d1 = row + j;
            // row - col + n
            int d2 = row - j + max;

            if (diag1[d1] || diag2[d2] || col[j]) {
                continue;
            }

            // mark placed queen
            col[j] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            dfsHelper(max, row + 1, diag1, diag2, col);
            col[j] = false;
            diag1[d1] = false;
            diag2[d2] = false;

        }
    }
}
```