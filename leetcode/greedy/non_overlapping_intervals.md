### [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:
```
Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
```
Example 2:
```
Input: [[1,2],[1,2],[1,2]]
Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
```
Example 3:
```
Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
``` 

Note:

- You may assume the interval's end point is always bigger than its start point.
- Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

### Solution

#### Approach 1: [Greedy](https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization)

##### Complexity analysis:
- Time complexity: O(nlogn)
- Space complexity: O(1)

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length < 2) {
            return 0;
        }

        // the old java comparator way:
        // Comparator<int[]> cmp = new Comparator<>() {
        //     @Override
        //     public int compare(int[] a, int[] b) {
        //         // sort the end time from early to late
        //         return a[1] - b[1];
        //     }
        // };

        // lambda way https://blog.csdn.net/u014042066/article/details/76248692
        Arrays.sort(intervals, (int[] a, int[] b) -> a[1] - b[1]);
        // count how many pairs doesn't have conflicts
        int end = intervals[0][1];
        // b/c we start from the next interval
        int count = 1;

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}
```