### 85. Maximal Rectangle
https://leetcode.com/problems/maximal-rectangle/

Example:
```
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
```

[Solution:](https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution)

用这个数组　[0 1 0 1 1]　去追一遍代码，就能了解这个解法的牛逼之处。
```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int[] left = new int[col];
        Arrays.fill(left, 0);

        int[] right = new int[col];
        Arrays.fill(right, col);

        int[] height = new int[col];
        Arrays.fill(height, 0);

        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            // for height
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            int currLeft = 0;
            // for left
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], currLeft);
                } else {
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }

            int currRight = col;
            // for right
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currRight);
                } else {
                    right[j] = col;
                    currRight = j;
                }
            }

            // store max area
            for (int j = 0; j < col; j++) {
                int tempArea = (right[j] - left[j]) * height[j];
                maxArea = Math.max(maxArea, tempArea);
            }
        }
        return maxArea;
    }
}
```

// todo histogram approach