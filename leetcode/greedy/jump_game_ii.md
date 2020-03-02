### 45. Jump Game II

https://leetcode.com/problems/jump-game-ii/

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:
```
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
Note:

You can assume that you can always reach the last index.

Solution
The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps, as the following: 

This is an implicit bfs solution. i == curEnd means you visited all the items on the current level. Incrementing jumps++ is like incrementing the level you are on. And curEnd = curFarthest is like getting the queue size (level size) for the next level you are traversing.

```java
class Solution {
    public int jump(int[] nums) {
        int curFarest = 0;
        int curEnd = 0;
        int jumpCount = 0;
        // The reason we used i < length-1 is because it excludes the last value in nums. We don't need to care about furthestJump we can get from the last element.

        for (int i = 0; i < nums.length - 1; i++) {
            curFarest = Math.max(curFarest, nums[i] + i);
            if (curEnd == i) {
                jumpCount++;
                curEnd = curFarest;
            }
        }
        return jumpCount;
    }
}
```