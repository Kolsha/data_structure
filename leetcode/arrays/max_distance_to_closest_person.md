### 849. Maximize Distance to Closest Person
https://leetcode.com/problems/maximize-distance-to-closest-person/

In a row of `seats`, `1` represents a person sitting in that seat, and `0` represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:
```
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
```
Example 2:
```
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
```
Note:

1. 1 <= seats.length <= 20000
2. seats contains only 0s or 1s, at least one 0, and at least one 1.

Solution:
from: https://leetcode.com/problems/maximize-distance-to-closest-person/discuss/137912/JavaC%2B%2BPython-One-pass-Easy-Understood

Find the max distance between 1s and finally check with the last distance in the end.

| value  | 1  | 0  | 0  | 0  | 1  | 0  |  0 | 0  | 0  |
|---|---|---|---|---|---|---|---|---|---|
| index  | 0  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  |

```java
class Solution {
    public int maxDistToClosest(int[] seats) {
        int last = -1, res = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                res = (last < 0) ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        return Math.max(seats.length - last - 1, res);
    }
}
```