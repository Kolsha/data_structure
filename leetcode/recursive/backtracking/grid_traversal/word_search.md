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

Approach 1: backtracking

Complexity analysis

<ul>
<li>
<p>Time Complexity: <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>⋅</mo><msup><mn>3</mn><mi>L</mi></msup><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N \cdot 3 ^ L)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">⋅</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:1.0913309999999998em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord">3</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.8413309999999999em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">L</span></span></span></span></span></span></span></span><span class="mclose">)</span></span></span></span> where <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>N</mi></mrow><annotation encoding="application/x-tex">N</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.68333em;vertical-align:0em;"></span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span></span></span></span> is the number of cells in the board and <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>L</mi></mrow><annotation encoding="application/x-tex">L</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.68333em;vertical-align:0em;"></span><span class="mord mathdefault">L</span></span></span></span> is the length of the word to be matched.</p>
<ul>
<li>
<p>For the backtracking function, initially we could have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from).
As a result, the execution trace after the first step could be visualized as a 3-ary tree, each of the branches represent a potential exploration in the corresponding direction. Therefore, in the worst case, the total number of invocation would be the number of nodes in a full 3-nary tree, which is about <span class="katex"><span class="katex-mathml"><math><semantics><mrow><msup><mn>3</mn><mi>L</mi></msup></mrow><annotation encoding="application/x-tex">3^L</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.8413309999999999em;vertical-align:0em;"></span><span class="mord"><span class="mord">3</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.8413309999999999em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">L</span></span></span></span></span></span></span></span></span></span></span>.</p>
</li>
<li>
<p>We iterate through the board for backtracking, <em>i.e.</em> there could be <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>N</mi></mrow><annotation encoding="application/x-tex">N</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.68333em;vertical-align:0em;"></span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span></span></span></span> times invocation for the backtracking function in the worst case.</p>
</li>
<li>
<p>As a result, overall the time complexity of the algorithm would be <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>⋅</mo><msup><mn>3</mn><mi>L</mi></msup><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N \cdot 3 ^ L)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">⋅</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:1.0913309999999998em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord">3</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.8413309999999999em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">L</span></span></span></span></span></span></span></span><span class="mclose">)</span></span></span></span>.</p>
</li>
</ul>
</li>
<li>
<p>Space Complexity: <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>L</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(L)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault">L</span><span class="mclose">)</span></span></span></span> where <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>L</mi></mrow><annotation encoding="application/x-tex">L</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.68333em;vertical-align:0em;"></span><span class="mord mathdefault">L</span></span></span></span> is the length of the word to be matched.</p>
<ul>
<li>The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length of the call stack would be the length of the word. Therefore, the space complexity of the algorithm is <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>L</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(L)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault">L</span><span class="mclose">)</span></span></span></span>.
<br>
<br></li>
</ul>
</li>
</ul>

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
        if(row < 0 || row >= maxRow || col < 0 || col >= maxCol || board[row][col] != word.charAt(index)) {
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
            res = backtrack(board, nr, nc, word, index + 1);
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