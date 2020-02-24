### 79. Word Search

https://leetcode.com/problems/word-search/

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

Solution

```java
class Solution {
    public boolean exist(char[][] board, String word) {
      int row = board.length;
      int col = board[0].length;

      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(backtrack(board, i, j, word, 0)) {
                  return true;
              }
          }
      }
      return false;
    }
    private boolean backtrack(char[][] board, int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if(index >= word.length()) {
            return true;
        }
        int maxRow = board.length;
        int maxCol = board[0].length;
        
        /* Step 2). Check the boundaries. */
        if(row < 0 || row >= maxRow || col < 0 || col >= maxCol || 
           board[row][col] != word.charAt(index)) {
            return false;
        }
        
        // mark the path before the next exploration
        board[row][col] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        /* Step 3). explore the neighbors in DFS */
        boolean res = false;
        for(int i = 0; i < dirs.length; i++) {
            int nr = row+dirs[i][0];
            int nc = col+dirs[i][1];
            res = backtrack(board, nr, nc, word, index+1);
            if(res) {
                break;
            }
        }
        
        /* Step 4). clean up and return the result. */
        board[row][col] = word.charAt(index);
        return res;
    }
}
```