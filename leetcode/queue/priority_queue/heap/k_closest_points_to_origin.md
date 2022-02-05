[973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the X-Y plane and an integer `k`, return the `k` closest points to the origin `(0, 0)`.

The distance between two points on the X-Y plane is the Euclidean distance (i.e., `√(x1 - x2)2 + (y1 - y2)2)`.

You may return the answer in **any order**. The answer is **guaranteed to be unique** (except for the order that it is in).



Example 1:
![](https://assets.leetcode.com/uploads/2021/03/03/closestplane1.jpg)
```
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
```
Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
``` 

Constraints:

- 1 <= k <= points.length <= 10^4
- $-10^4 < x_i, y_i < 10^4$

##### Solution

##### Approach 1: Priority Queue

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Comparator<int[]> cmp = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                // large -> small
                return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<>(cmp);
        for (int[] pt : points) {
            queue.offer(pt);
            if (queue.size() > K) {
                // remove the first element in PriorityQueue
                queue.poll();
            }
        }
        int[][] res = new int[K][2];
        while (!queue.isEmpty()) {
            res[--K] = queue.poll();
        }
        return res;
    }
}
```