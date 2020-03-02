### 51. N-Queens

https://leetcode.com/problems/n-queens/

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

![](https://assets.leetcode.com/uploads/2018/10/12/8-queens.png)

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:
```
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
```

Solution
```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        // init a nxn chessboard
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        ArrayList<List<String>> res = new ArrayList<>();

        dfsHelper(board, 0, res);
        return res;
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
                // (Math.abs(row - i) == Math.abs(col - j) -> (row - i == col - j) || (row+ col
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