### 1266. Minimum Time Visiting All Points

https://leetcode.com/problems/minimum-time-visiting-all-points/

On a plane there are `n` points with integer coordinates `points[i] = [xi, yi]`. Your task is to find the minimum time in seconds to visit all points.

You can move according to the next rules:

- In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
- You have to visit the points in the same order as they appear in the array.

Example 1:

![](https://assets.leetcode.com/uploads/2019/11/14/1626_example_1.PNG)
```
Input: points = [[1,1],[3,4],[-1,0]]
Output: 7
Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]   
Time from [1,1] to [3,4] = 3 seconds 
Time from [3,4] to [-1,0] = 4 seconds
Total time = 7 seconds
```

Example 2:
```
Input: points = [[3,2],[-2,2]]
Output: 5
```
Constraints:

- points.length == n
- 1 <= n <= 100
- points[i].length == 2
- -1000 <= points[i][0], points[i][1] <= 1000

Solution:
We will move diagonally as much as possible, so:
`min(deltaX, deltaY)`: (By drawing the coordinates,) we can see inside the square, moving diagonally, moving horizonally and moving vertically takes the same amount of time. `Therefore, min(deltaX, deltaY) will be the time needed for moving diagonally`.

`abs(deltaX - deltaY):` (By drawing the coordinates, you can find that) `It will be the remaining time to reach the next point after moving diagonally.`

```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0, n = points.length;
        for (int i = 0; i < n - 1; i++) {
            int deltaX = Math.abs(points[i + 1][0] - points[i][0]);
            int deltaY = Math.abs(points[i + 1][1] - points[i][1]);
            ans += Math.min(deltaX, deltaY) + Math.abs(deltaX - deltaY);
        }
        return ans;
    }
}
```
