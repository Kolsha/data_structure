### 1011. Capacity To Ship Packages Within D Days
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

A conveyor belt has packages that must be shipped from one port to another within `D` days.

The i-th package on the conveyor belt has a weight of `weights[i]`.  Each day, we load the ship with packages on the conveyor belt (in the order given by `weights`). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within `D` days.

**Example 1:**
```
Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation: 
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed. 
```

**Example 2:**
```
Input: weights = [3,2,2,4,1,4], D = 3
Output: 6
Explanation: 
A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
```

**Note:**
```
1. 1 <= D <= weights.length <= 50000
2. 1 <= weights[i] <= 500
```

**Solution:**
在这一题中，船的最小运载容量是有固定范围的【最小：所有包裹的最大值（因为包裹不可分割），最大：数组所有重量的总和】，我们所要求的解为最小可在时间内完成运输的船。所以是典型的 Binary Search。
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int lo = 1;
        int hi = 1;
        for (int weight : weights) {
            lo = Math.max(lo, weight);
            hi += weight;
        }

        while (lo <= hi) {
            int m = lo + ((hi - lo) >> 1);
            if (canFinish(weights, m, D)) {
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }
        return lo;
    }

    private boolean canFinish(int[] weights, int cap, int day) {
        // 这边最小从 1 开始，因为不管第一艘船有没有满，都会开出，算一天。
        // 如果不懂可以假设一个具有超大容量的船，将所有货戴完后仍有多馀容量，
        // 很明显，此时 count 的值应为 1，而不是 0.
        int count = 1;
        int curWeight = 0;
        for (int weight : weights) {
            if ((weight + curWeight) > cap) {
                count++;
                curWeight = 0;
            }
            curWeight += weight;
        }
        return count <= day;
    }

}
```

```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w : weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D){
                left = mid + 1;                
            } else {
                right = mid;                
            }
        }
        return left;
    }
}
```