### [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions/)

Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

 

Example 1:

![](https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg)

```
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
```
Example 2:
```
Input: board = [["X"]]
Output: [["X"]]
```

Constraints:

- m == board.length
- n == board[i].length
- 1 <= m, n <= 200
- board[i][j] is 'X' or 'O'.

### Solution

#### Approach 1: Union Find

##### Logical Thought
We aim to find all 'O's such that it is on the border or it is connected to an 'O' on the border. If we regard 'O' mentioned above as a node (or an element), the problem becomes to find the connected components (or disjoint sets) connected to borders. So-called borders should also be represented as an element, so elements connected to it can be merged with it into a set. That's the usage of **dummyBorder**.
Pseudo-code

initialize dummyRepresentative
```java
for O in board
	if O is on border
		union(dummyBorder, O)
	else
		for neighbour of O
			if (neighbour is 'O') 
				union(neighbour, O)
                
for each cell
	if cell is 'O' && (find(cel) != find(dummyBorder))
		flip
```


```java
class Solution {
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};    
        
    public void solve(char[][] board) {
        
        if (board == null || board.length == 0) {
            return;
        }       
       
        DisjointSets disjointSets = new DisjointSets(board);
        int rows = board.length, cols = board[0].length;
        int dummyBorder = rows * cols;
        
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y] == 'O') {
                    int borderO = x * cols + y;
                    if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
                        disjointSets.union(dummyBorder, borderO);
                        continue;
                    }
                    for (int[] dir : directions) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && board[nx][ny] == 'O') {
                            int neighbor = nx * cols + ny;
                            disjointSets.union(borderO, neighbor);
                        }
                    }
                }
            }
        }
        
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y] == 'O' && disjointSets.find(x * cols + y) != disjointSets.find(dummyBorder)) {
                    board[x][y] = 'X';
                }
            }
        }
        
    }
    
    class DisjointSets {
        
        int[] parent;
        
        public DisjointSets(char[][] board) {
            int rows = board.length, cols = board[0].length;
            parent = new int[rows * cols + 1];
            
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {       
                    if (board[x][y] == 'O') {
                        int id = x * cols + y;
                        parent[id] = id;
                    }
                }
            }
            parent[rows * cols] = rows * cols;
        }
        
        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}
```