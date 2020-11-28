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

##### Approach 1: Backtracking with Trie

###### Intuition

The problem is actually a simplified crossword puzzle game, where the word solutions have been given on the board embedded with some noise letters. All we need to to do is to cross them out.

Intuitively, in order to cross out all potential words, the overall strategy would be to iterate the cell one by one, and from each cell we walk along its neighbors in four potential directions to find matched words. While wandering around the board, we would stop the exploration when we know it would not lead to the discovery of new words.

One might have guessed the paradigm that we would use for this problem. Yes, it is backtracking, which would be the backbone of the solution. It is fairly simply to construct a solution of backtracking. One could even follow a template given in our [Explore card of Recursion II](https://leetcode.com/explore/learn/card/recursion-ii/472/backtracking/2793/).

The key of the solution lies on how we find the matches of word from the dictionary. Intuitively, one might resort to the hashset data structure (e.g. set() in Python). This could work.

However, during the backtracking process, one would encounter more often the need to tell if there exists any word that contains certain prefix, rather than if a string exists as a word in the dictionary. Because if we know that there does not exist any match of word in the dictionary for a given prefix, then we would not need to further explore certain direction. And this, would greatly reduce the exploration space, therefore improve the performance of the backtracking algorithm.

The capability of finding matching prefix is where the data structure called Trie would shine, comparing the hashset data structure. Not only can Trie tell the membership of a word, but also it can instantly find the words that share a given prefix. As it turns out, the choice of data structure (Trie VS. hashset), which could end with a solution that ranks either the top $5\%$ or the bottom $5\%$.

Here we show an example of Trie that is built from a list of words. As one can see from the following graph, at the node denoted d, we would know there are at least two words with the prefix d from the dictionary.

![](https://leetcode.com/problems/word-search-ii/Figures/212/212_trie_example.png)

We have a problem about [implementing a Trie data structure](https://leetcode.com/problems/implement-trie-prefix-tree/). One can start with the Trie problem as warm up, and come back this problem later.

###### Algorithm

The overall workflow of the algorithm is intuitive, which consists of a loop over each cell in the board and a recursive function call starting from the cell. Here is the skeleton of the algorithm.

- We build a Trie out of the words in the dictionary, which would be used for the matching process later.

- Starting from each cell, we start the backtracking exploration (i.e. backtracking(cell)), if there exists any word in the dictionary that starts with the letter in the cell.

- During the recursive function call backtracking(cell), we explore the neighbor cells (i.e. neighborCell) around the current cell for the next recursive call backtracking(neighborCell). At each call, we check if the sequence of letters that we traverse so far matches any word in the dictionary, with the help of the Trie data structure that we built at the beginning.

Here is an overall impression how the algorithm works. We give some sample implementation based on the rough idea above. And later, we detail some optimization that one could further apply to the algorithm.
![](https://leetcode.com/problems/word-search-ii/Figures/212/212_trie_algo.png)

```java
class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;
  public TrieNode() {}
}

class Solution {
  char[][] _board = null;
  ArrayList<String> _result = new ArrayList<String>();

  public List<String> findWords(char[][] board, String[] words) {

    // Step 1). Construct the Trie
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode node = root;

      for (Character letter : word.toCharArray()) {
        if (node.children.containsKey(letter)) {
          node = node.children.get(letter);
        } else {
          TrieNode newNode = new TrieNode();
          node.children.put(letter, newNode);
          node = newNode;
        }
      }
      node.word = word;  // store words in Trie
    }

    this._board = board;
    // Step 2). Backtracking starting for each cell in the board
    for (int row = 0; row < board.length; ++row) {
      for (int col = 0; col < board[row].length; ++col) {
        if (root.children.containsKey(board[row][col])) {
          backtracking(row, col, root);
        }
      }
    }

    return this._result;
  }
  
  private void backtracking(int row, int col, TrieNode parent) {
    Character letter = this._board[row][col];
    TrieNode currNode = parent.children.get(letter);

    // check if there is any match
    if (currNode.word != null) {
      this._result.add(currNode.word);
      currNode.word = null;
    }

    // mark the current letter before the EXPLORATION
    this._board[row][col] = '#';

    // explore neighbor cells in around-clock directions: up, right, down, left
    int[] rowOffset = {-1, 0, 1, 0};
    int[] colOffset = {0, 1, 0, -1};
    for (int i = 0; i < 4; ++i) {
      int newRow = row + rowOffset[i];
      int newCol = col + colOffset[i];
      if (newRow < 0 || newRow >= this._board.length || newCol < 0
          || newCol >= this._board[0].length) {
        continue;
      }
      if (currNode.children.containsKey(this._board[newRow][newCol])) {
        backtracking(newRow, newCol, currNode);
      }
    }

    // End of EXPLORATION, restore the original letter in the board.
    this._board[row][col] = letter;

    // Optimization: incrementally remove the leaf nodes
    if (currNode.children.isEmpty()) {
      parent.children.remove(letter);
    }
  }
}

```

###### Optimizations
In the above implementation, we applied a few tricks to further speed up the running time, in addition to the application of the Trie data structure. In particular, the Python implementation could run faster than 98% of the submissions. We detail the tricks as follows, ordered by their significance.

Backtrack along the nodes in Trie.

- One could use Trie simply as a dictionary to quickly find the match of words and prefixes, i.e. at each step of backtracking, we start all over from the root of the Trie. This could work.

- However, a more efficient way would be to traverse the Trie together with the progress of backtracking, i.e. at each step of backtracking(TrieNode), the depth of the TrieNode corresponds to the length of the prefix that we've matched so far. This measure could lift your solution out of the bottom $5\%$ of submissions.

Gradually prune the nodes in Trie during the backtracking.

- The idea is motivated by the fact that the time complexity of the overall algorithm sort of depends on the size of the Trie. For a leaf node in Trie, once we traverse it (i.e. find a matched word), we would no longer need to traverse it again. As a result, we could prune it out from the Trie.

- Gradually, those non-leaf nodes could become leaf nodes later, since we trim their children leaf nodes. In the extreme case, the Trie would become empty, once we find a match for all the words in the dictionary. This pruning measure could reduce up to $50\%$ of the running time for the test cases of the online judge.

![](https://leetcode.com/problems/word-search-ii/Figures/212/212_trie_prune.png)

Keep words in the Trie.

- One might use a flag in the Trie node to indicate if the path to the current code match any word in the dictionary. It is not necessary to keep the words in the Trie.
- However, doing so could improve the performance of the algorithm a bit. One benefit is that one would not need to pass the prefix as the parameter in the backtracking() call. And this could speed up a bit the recursive call. Similarly, one does not need to reconstruct the matched word from the prefix, if we keep the words in Trie.

Remove the matched words from the Trie.

- In the problem, we are asked to return all the matched words, rather than the number of potential matches. Therefore, once we reach certain Trie node that contains a match of word, we could simply remove the match from the Trie.
- As a side benefit, we do not need to check if there is any duplicate in the result set. As a result, we could simply use a list instead of set to keep the results, which could speed up the solution a bit.


##### Complexity analysis

- Time complexity: $\mathcal{O}(M(4\cdot3^{L-1}))$, where $M$ is the number of cells in the board and LL is the maximum length of words.

    - It is tricky is calculate the exact number of steps that a backtracking algorithm would perform. We provide a upper bound of steps for the worst scenario for this problem. The algorithm loops over all the cells in the board, therefore we have MM as a factor in the complexity formula. It then boils down to the maximum number of steps we would need for each starting cell (i.e.$4\cdot3^{L-1}$).

    - Assume the maximum length of word is LL, starting from a cell, initially we would have at most 4 directions to explore. Assume each direction is valid (i.e. worst case), during the following exploration, we have at most 3 neighbor cells (excluding the cell where we come from) to explore. As a result, we would traverse at most $4\cdot3^{L-1}$ cells during the backtracking exploration.

    - One might wonder what the worst case scenario looks like. Well, here is an example. Imagine, each of the cells in the board contains the letter a, and the word dictionary contains a single word ['aaaa']. Voila. This is one of the worst scenarios that the algorithm would encounter. ![](https://leetcode.com/problems/word-search-ii/Figures/212/212_complexity_example.png)

    - Note that, the above time complexity is estimated under the assumption that the Trie data structure would not change once built. If we apply the optimization trick to gradually remove the nodes in Trie, we could greatly improve the time complexity, since the cost of backtracking would reduced to zero once we match all the words in the dictionary, i.e. the Trie becomes empty.


- Space Complexity: $\mathcal{O}(N)$, where $N$ is the total number of letters in the dictionary.

  - The main space consumed by the algorithm is the Trie data structure we build. In the worst case where there is no overlapping of prefixes among the words, the Trie would have as many nodes as the letters of all words. And optionally, one might keep a copy of words in the Trie as well. As a result, we might need $2N$ space for the Trie.