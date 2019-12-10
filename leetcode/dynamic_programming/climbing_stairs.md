### 70. Climbing Stairs
https://leetcode.com/problems/climbing-stairs/

You are climbing a stair case. It takes `n` steps to reach to the top.

Each time you can either climb `1` or `2` steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:
```
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```
Example 2:
```
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

**Solution:**<br/>
- Define subproblems<br/>
Let D<sub>n</sub> be the number of ways to climb the stairs.
- Find the recurrence<br/>
Since we can climb 1 stair or 2 stairs at a time, in the final phase, we will only have 1 or 2 stairs left.<br/>
 或者可以这样想：n 个阶梯的走法，是由第一步为一阶的走法(也就是走剩下的 n - 1 阶的走法) + 第一步为二阶的走法所组成( 也就是走 n - 2 阶的走法)。<br/>
&rarr; D<sub>n</sub> = D<sub>n - 1</sub> + D<sub>n - 2</sub><br/>

- Solve the base cases
  1. D<sub>1</sub> = 1, D<sub>2</sub> = 2


```java
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int first = 1;
        int second = 2;
        int result = 0;

        for (int i = 3; i < n + 1; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }
}
```