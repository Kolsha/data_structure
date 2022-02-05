### [240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)


Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

- Integers in each row are sorted in ascending from left to right.
- Integers in each column are sorted in ascending from top to bottom.
##### Example:

Consider the following matrix:
```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```
Given target = 5, return `true`.

Given target = 20, return `false`.

##### Solution

##### Approach 1: Binary search in diagonal direction in Matrix
##### Intuition
The fact that the matrix is **sorted** suggests that there must be some way to use binary search to speed up our algorithm.

##### Algorithm

First, we ensure that `matrix` is not `null` and not empty. Then, if we iterate over the matrix diagonals, we can maintain an invariant that the slice of the row and column beginning at the current (row, col) pair is sorted. Therefore, we can always **binary search these row and column slices for target**. We proceed in a logical fashion, iterating over the diagonals, binary searching the rows and columns until we either run out of diagonals (meaning we can return False) or find target (meaning we can return True). The **binarySearch** function works just like normal binary search, but is made ugly by the need to search both rows and columns of a two-dimensional array.

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        // iterate over matrix diagonals
        int dim = Math.min(matrix[0].length, matrix.length);
        for(int i = 0; i < dim; i++) {
            boolean foundHorizonal = foundInHorizontal(matrix, i, target, true);
            boolean foundVertical = foundInHorizontal(matrix, i, target, false);
            if(foundHorizonal || foundVertical) {
                return true;
            }
        }
        return false;
    }
    
    private boolean foundInHorizontal(int[][] matrix, int start, int target, boolean isHorizontal) {
        int lo = start;
        int hi = isHorizontal? matrix[0].length - 1: matrix.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/ 2;
            int midValue = isHorizontal? matrix[start][mid] : matrix[mid][start];
            if(midValue == target) {
                return true;
            } else if (midValue < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
```

##### Approach 2: Search Space Reduction
##### Intuition
Because the rows and columns of the matrix are sorted (from left-to-right and top-to-bottom, respectively), we can prune `O(m)` or `O(n)` elements when looking at any particular value.

Algorithm

First, we initialize a (row, col) pointer to the bottom-left of the matrix.[1] Then, until we find target and return true (or the pointer points to a (row, col) that lies outside of the dimensions of the matrix), we do the following: **if the currently-pointed-to value is larger than target we can move one row "up". Otherwise, if the currently-pointed-to value is smaller than target, we can move one column "right". It is not too tricky to see why doing this will never prune the correct answer**; because the rows are sorted from left-to-right, we know that every value to the right of the current value is larger. Therefore, if the current value is already larger than target, we know that every value to its right will also be too large. A very similar argument can be made for the columns, so this manner of search will always find target in the matrix (if it is present).

Check out some sample runs of the algorithm in the animation below:
![](./res/2d_binary_search_ex1.gif)
![](./res/2d_binary_search_ex2.gif)

##### Complexity analysis:
- Time complexity: O(N + M)
  The key to the time complexity analysis is noticing that, on every iteration (during which we do not return true) either row or col is is decremented/incremented exactly once. Because row can only be decremented mm times and col can only be incremented nn times before causing the while loop to terminate, the loop cannot run for more than n+mn+m iterations. Because all other work is constant, the overall time complexity is linear in the sum of the dimensions of the matrix.
- Space complexity: O(1)

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
}
```