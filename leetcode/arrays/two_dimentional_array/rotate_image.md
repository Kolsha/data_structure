### 48. Rotate Image

https://leetcode.com/problems/rotate-image/

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
```
Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```
Example 2:
```
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

Solution

O(n^2) time and O(1) space algorithm ( without any workarounds and hanky-panky stuff! )

**Rotate by +90:**
Transpose
Reverse each row

**Rotate by -90:**
Method 1 :
Transpose
Reverse each column

Method 2 :
Reverse each row
Transpose

**Rotate by +180:**
Method 1: Rotate by +90 twice

Method 2: Reverse each row and then reverse each column (Transpose)

**Rotate by -180:**
Method 1: Rotate by -90 twice

Method 2: Reverse each column and then reverse each row

Method 3: Rotate by +180 as they are same

=== 
Approach 1: Transpose and then reverse

ex:
```
1 2 3
4 5 6
7 8 9

Step 1: Transpose
1 4 7
2 5 8
3 6 9

Step 2: Reverse each row
7 4 1
8 5 2
9 6 3
```
Complexity analysis:
- Time complexity: O(N^2)
- Space complexity: O(1)

```java
class Solution {
    public void rotate(int[][] matrix) {
        // step 1. Transpose
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        
        // step 2. Reverse each row
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
}
```