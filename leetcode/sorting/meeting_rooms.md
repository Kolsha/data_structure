### [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/) <sup>:star:</sup>


Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:
```
Input: [[0,30],[5,10],[15,20]]
Output: false
```
Example 2:
```
Input: [[7,10],[2,4]]
Output: true
```
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

Solution

```java
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Comparator<int[]> comp = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        };
        
        Arrays.sort(intervals, comp);
        for(int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            int[] next = (i + 1 < intervals.length)? intervals[i + 1] : null;
            if (next!=null) {
                if(curr[1] > next[0]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#facebook