### 56. Merge Intervals
https://leetcode.com/problems/merge-intervals/

Given a collection of intervals, merge all overlapping intervals.

Example 1:
```
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
```
Example 2:
```
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
```

Solution
[Method 1](https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution)
The idea is to sort the intervals by their starting points. Then, we take the first interval and compare its end with the next intervals starts. As long as they overlap, we update the end to be the max end of the overlapping intervals. Once we find a non overlapping interval, we can add the previous "extended" interval and start over.

Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n)).

I used a lambda comparator (Java 8) and a for-each loop to try to keep the code clean and simple.

EDIT: The function signature changed in april 2019.
Here is a new version of the algorithm with arrays. To make more memory efficient, I reused the initial array (sort of "in-place") but it would be easy to create new subarrays if you wanted to keep the initial data.
It takes less memory than 99% of the other solutions (sometimes 90% depending on the run) and is more than 10 times faster than the previous version with lists.
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        ArrayList<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```