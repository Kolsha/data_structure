### 221. Maximal Square
https://leetcode.com/problems/maximal-square/

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
```
Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
```

Solution:
```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int result = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    int left = dp[i][j - 1];
                    int top = dp[i - 1][j];
                    int topLeft = dp[i - 1][j - 1];
                    dp[i][j] = getMin(left, top, topLeft) + 1;
                    result = Math.max(dp[i][j], result);
                }

            }
        }
        return result * result;
    }

    int getMin(int... array) {
        Arrays.sort(array);
        return array[0];
    }
}
```

Similar Problem:
[Count Square](./count_square_submatrices.md)