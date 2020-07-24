### 59. Spiral Matrix II

https://leetcode.com/problems/spiral-matrix-ii/

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:
```
Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

Solution
Approach: ad-hoc

Complexity Analysis
- Time complexity: O(n)
- Space complexity: O(1)
```java
class Solution {
    public static int[][] generateMatrix(int n) {
	    int[][] ret = new int[n][n];
	    int left = 0,top = 0;
	    int right = n -1,down = n - 1;
	    int count = 1;
	    while (left <= right) {
		    for (int j = left; j <= right; j ++) {
		    	ret[top][j] = count++; // move right until the `right`
		    }
		    top++;                    // lower the ceiling
		    for (int i = top; i <= down; i ++) {
			    ret[i][right] = count++; // move down until reach `down`
		    }
		    right--;                  // shrink from right
		    for (int j = right; j >= left; j --) {
		    	ret[down][j] = count++; // move left until reach `left`
		    }
		    down--;                    // increase the floor
		    for (int i = down; i >= top; i --) {
			    ret[i][left] = count ++; // move up until reach `top`
		    }
		    left ++;
	    }
	    return ret;
    }
}
```
