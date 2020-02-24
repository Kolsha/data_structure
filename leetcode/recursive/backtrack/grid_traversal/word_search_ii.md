### 212. Word Search II

https://leetcode.com/problems/word-search-ii/


Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:
```
Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
``` 

Note:

1. All inputs are consist of lowercase letters a-z.
2. The values of words are distinct.


Solution
Method 1: Use 'Word Search' algorithm
```java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> res = new ArrayList<>();
        for (String word : words) {
            if (isExist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isExist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (backtrack(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int row, int col, String word, int index) {
        if (index >= word.length()) {
            return true;
        }

        int maxRow = board.length;
        int maxCol = board[0].length;

        if (row < 0 || row >= maxRow || col < 0 || col >= maxCol || board[row][col] != word.charAt(index)) {
            return false;
        }

        // mark visited
        board[row][col] = '#';
        boolean result = false;
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < dirs.length; i++) {
            int nextRow = row + dirs[i][0];
            int nextCol = col + dirs[i][1];
            result = backtrack(board, nextRow, nextCol, word, index + 1);
            if (result) {
                break;
            }
        }

        board[row][col] = word.charAt(index);
        return result;

    }
}
```