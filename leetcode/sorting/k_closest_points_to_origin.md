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

##### Approach 1: Simple Sorting

##### Complexity analysis
- Time Complexity: O(nlogn)

- Space Complexity: O(1)

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

##### Approach 2: Priority Queue
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

##### Approach 3: Divide and Conquer [Time limit exceed]
###### Intuition

We want an algorithm faster than N \log NNlogN. Clearly, the only way to do this is to use the fact that the K elements returned can be in any order -- otherwise we would be sorting which is at least N \log NNlogN.

Say we choose some random element x = A[i] and split the array into two buckets: one bucket of all the elements less than x, and another bucket of all the elements greater than or equal to x. This is known as "quickselecting by a pivot x".

The idea is that if we quickselect by some pivot, on average in linear time we'll reduce the problem to a problem of half the size.

###### Algorithm

Let's do the work(i, j, K) of partially sorting the subarray (points[i], points[i+1], ..., points[j]) so that the smallest K elements of this subarray occur in the first K positions (i, i+1, ..., i+K-1).

First, we quickselect by a random pivot element from the subarray. To do this in place, we have two pointers i and j, and move these pointers to the elements that are in the wrong bucket -- then, we swap these elements.

After, we have two buckets [oi, i] and [i+1, oj], where (oi, oj) are the original (i, j) values when calling work(i, j, K). Say the first bucket has 10 items and the second bucket has 15 items. If we were trying to partially sort say, K = 5 items, then we only need to partially sort the first bucket: work(oi, i, 5). Otherwise, if we were trying to partially sort say, K = 17 items, then the first 10 items are already partially sorted, and we only need to partially sort the next 7 items: work(i+1, oj, 7).

##### Complexity Analysis

- Time Complexity: $O(N)$ in average case and $O(N^2)$ in the worst case, where NN is the length of points.

- Space Complexity: $O(N)$.

```java
import java.util.concurrent.ThreadLocalRandom;

class Solution {
    int[][] points;
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength)
            sort(i, mid - 1, K);
        else if (K > leftLength)
            sort(mid + 1, j, K - leftLength);
    }

    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;

        while (true) {
            while (i < j && dist(i) < pivot)
                i++;
            while (i <= j && dist(j) > pivot)
                j--;
            if (i >= j) break;
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
}
```
