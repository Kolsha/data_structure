### 253. Meeting Rooms II

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:
```
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
```
Example 2:
```
Input: [[7,10],[2,4]]
Output: 1
```
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

Solution

Method 1: PriorityQueue

Time complexity:

Space complexity:

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // valid input or handle edge cases
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // sort the intervals based on start time
        Comparator<int[]> startTimeComp = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        };
        Arrays.sort(intervals, startTimeComp);
        
        Comparator<int[]> endTimeComp = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        };
        PriorityQueue<int[]> heap = new PriorityQueue<>(endTimeComp);
        
        heap.offer(intervals[0]);

        for(int i = 1; i < intervals.length; i++) {
            int[] officeHour = heap.poll();
            int[] interval = intervals[i];
            // if we can use after previous meeting
            if(officeHour[1] <= interval[0]) {
                officeHour[1] = interval[1];
            } else {
                // if we can't use after previous meeting, then we need to get another office
                heap.offer(interval);
            }
            // add back
            heap.offer(officeHour);
        }
        return heap.size();
    }
}
```

Method 2: Optimal solution
// todo

#facebook