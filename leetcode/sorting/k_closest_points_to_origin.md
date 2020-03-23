### 973. K Closest Points to Origin

https://leetcode.com/problems/k-closest-points-to-origin/

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:
```
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
```
Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
``` 

Note:

1. 1 <= K <= points.length <= 10000
2. -10000 < points[i][0] < 10000
3. -10000 < points[i][1] < 10000

Solution

Method 1: Simple Sorting

Time Complexity: O(nlogn)

Space Complexity: O(1)

In Java
```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Comparator<int[]> cmp = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]);
            }
        };
        Arrays.sort(points, cmp);
        return Arrays.copyOfRange(points, 0, K);
    }
}
```

Method 2: Priority Queue
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
