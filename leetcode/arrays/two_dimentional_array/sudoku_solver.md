### [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)


Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)
A sudoku puzzle...


![](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

...and its solution numbers marked in red.


Note:

- The given board contain only digits 1-9 and the character '.'.
- You may assume that the given Sudoku puzzle will have a single unique solution.
- The given board size is always 9x9.

Solution

```java
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.';

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            char rowMov = board[row][i];
            if (rowMov != '.' && rowMov == num) {
                return false;
            }
            char colMov = board[i][col];
            if (colMov != '.' && colMov == num) {
                return false;
            }
            // todo why?
            // region start row
            int rowIndex = 3 * (row / 3);
            // region start col
            int colIndex = 3 * (col / 3);
            char blkMov = board[rowIndex + i / 3][colIndex + i % 3];
            if (blkMov != '.' && blkMov == num) {
                return false;
            }
        }
        return true;
    }
}
```