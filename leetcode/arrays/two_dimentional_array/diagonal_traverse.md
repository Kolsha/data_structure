### 498. Diagonal Traverse

https://leetcode.com/problems/diagonal-traverse/

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 

Example:
```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]
```
Explanation:
![](https://assets.leetcode.com/uploads/2018/10/12/diagonal_traverse.png)
 

Note:

- The total number of elements of the given matrix will not exceed 10,000.

Solution

Method 1: Identify Walk patterns after hitting each boundry

Time complexity: O(m * n), m = number of rows, n = number of columns.

Space complexity: O(1).

Walk patterns:

- If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
- If out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
- If out of top border (row < 0) then row = 0; change walk direction.
- If out of left border (col < 0) then col = 0; change walk direction.

Otherwise, just go along with the current direction.


```java
class Solution {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int rowMax = matrix.length, colMax = matrix[0].length;

        int[] result = new int[rowMax * colMax];
        int row = 0, col = 0, d = 1;

        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[row][col];
            row -= d;
            col += d;

            if (row >= rowMax) { row = rowMax - 1; col += 2; d = -d;}
            if (col >= colMax) { col = colMax - 1; row += 2; d = -d;}
            if (row < 0) { row = 0; d = -d;}
            if (col < 0) { col = 0; d = -d;}
        }

        return result;
    }
}
```

#facebook