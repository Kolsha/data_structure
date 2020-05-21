### 311. Sparse Matrix Multiplication

https://leetcode.com/problems/sparse-matrix-multiplication/

Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:
```
Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
```

Solution

```java
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        // R0 x C0 = A0,0
        // R0 x C1 = A0,1
        // R0 x C2 = A0,3
        
        // R1 x C0 = A1, 0
        // R1 x C1 = A1, 1
        // R1 x C2 = A1, 2
        
        // You may assume that A's column number is equal to B's row number.
        int rowA = A.length;
        int colA = A[0].length;
        
        int rowB = B.length;
        int colB = B[0].length;
        
        int[][] res = new int[rowA][colB];
        for(int i = 0; i < rowA; i++) {
            for(int j = 0; j < colB; j++) {
                res[i][j] = getMultiply(A, B, i, j);
            }
        }
        return res;
    }
    
    private int getMultiply(int[][] A, int[][] B, int row, int col) {
        int sum = 0;
        for(int i = 0; i < A[0].length; i++) {
            sum += A[row][i] * B[i][col];
        }
        return sum;
    }
}
```