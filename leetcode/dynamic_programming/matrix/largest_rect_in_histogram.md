### 84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

![](https://assets.leetcode.com/uploads/2018/10/12/histogram.png)<br/>
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


![](https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png)<br/>
The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:
```
Input: [2,1,5,6,2,3]
Output: 10
```

[Solution](https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/225556/Java-solution-with-explanations-in-Chinese):

S1:双重遍历法
```java
class Solution {
    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j >= 0; j--) {
                if (heights[j] < minHeight) {
                    minHeight = heights[j];
                }
                int tempArea = (i - j + 1) * minHeight;
                max = Math.max(tempArea, max);
            }
        }
        return max;

    }
}
```

