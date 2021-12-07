### [223. Rectangle Area](https://leetcode.com/problems/rectangle-area/)

Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.

The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).

The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).

 

Example 1:
![](https://assets.leetcode.com/uploads/2021/05/08/rectangle-plane.png)
```
Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
Output: 45
```
Example 2:
```
Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
Output: 16
``` 

Constraints:

- $-10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4$

##### Solution

##### Approach 1

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaOfSqrA = (C-A) * (D-B);
        int areaOfSqrB = (G-E) * (H-F);
        
        int left = Math.max(A, E);
        int right = Math.min(G, C);
        int bottom = Math.max(F, B);
        int top = Math.min(D, H);
        
        //If overlap
        int overlap = 0;
        if(right > left && top > bottom) {
             overlap = (right - left) * (top - bottom);
        }
        
        return areaOfSqrA + areaOfSqrB - overlap;
    }
}
```