### 73. Set Matrix Zeroes

https://leetcode.com/problems/set-matrix-zeroes/

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:
```
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
Example 2:
```
Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```
Follow up:

- A straight forward solution using O(mn) space is probably a bad idea.
- A simple improvement uses O(m + n) space, but still not the best solution.
- Could you devise a constant space solution?


Solution

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        ArrayList<Point> list = new ArrayList<>();
        // find zeros
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    list.add(new Point(i, j));
                }
            }
        }
        
        for(Point pt: list) {
            fillZeros(matrix, pt.row, pt.col, row, col);
        }
    }
    
    private void fillZeros(int[][] matrix, int row, int col, int rowMax, int colMax) {
        // horizontal
        for(int j = 0; j < colMax; j++) {
            matrix[row][j] = 0;
        }
        
        // vertical
        for(int i = 0; i < rowMax; i++) {
            matrix[i][col] = 0;
        }
    }
    
    private static class Point {
        int row;
        int col;
        Point(int r, int c) {
            row = r;
            col = c;
        }
    }
}
```

// todo https://leetcode.com/problems/set-matrix-zeroes/discuss/?currentPage=1&orderBy=most_votes&query=