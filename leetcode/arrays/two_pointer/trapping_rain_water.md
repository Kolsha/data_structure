### 42. Trapping Rain Water

https://leetcode.com/problems/trapping-rain-water/

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:
```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

Solution

![](https://image.ibb.co/d6A2ZU/IMG-0139.jpg)
```java
class Solution {
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int max = 0;
        while (l < r) {
            leftMax = Math.max(leftMax, height[l]);
            rightMax = Math.max(rightMax, height[r]);
            if (leftMax < rightMax) {
                max += (leftMax - height[l]);
                l++;
            } else {
                max += (rightMax - height[r]);
                r--;
            }
        }
        return max;
    }
}
```
