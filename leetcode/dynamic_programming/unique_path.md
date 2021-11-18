### [62. Unique Paths](https://leetcode.com/problems/unique-paths/)

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

 

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)
```
Input: m = 3, n = 7
Output: 28
```
**Example 2:**
```
Input: m = 3, n = 2
Output: 3
```
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

**Example 3:**

```
Input: m = 7, n = 3
Output: 28
```

**Example 4:**
```
Input: m = 3, n = 3
Output: 6
```


**Constraints:**

- 1 <= m, n <= 100
- It's guaranteed that the answer will be less than or equal to $2 * 10^9$.

### Solution

### Approach 1: Math - Combination
If you observe it care fully, our robot has to move `n-1` steps right and `m-1` steps down in any order to reach the right bottom of the grid. You will understand this more with the following example.

**Example:** We had 3x7 grid, robot needs to take `(3-1) = 2` steps down overall and `7-1 = 6` steps right overall to reach the bottom right of the grid. This is a total of 8 steps. And the ordering doesn't matter like: `D D R R R R R R` or `D R D R R R R R` or `R R R R R R D D` will all do the job, and all of this is noting but the permutation of total steps. So this is nothing but number of ways of choose D in the 8 blanks or number of ways of choosing R in the 8 blanks which is nothing but $8 \choose 6$ or $8 \choose 2$ = 28

**Approach:** So, we only need to choose number of ways of picking right from the total steps or number of ways of choosing down from the total steps. Which is nothing but $total \choose right$ or $total \choose down$.


#### Complexity analysis
- Time complexity: $O(min(m, n))$
- Space complexity: 1

```java
 public class Solution {
    
     public int uniquePaths(int m, int n) {
         int total = m - 1 + n - 1;
         int r = Math.min(m, n) - 1;
         
         long res = 1;
         
         for(int i = 1; i <= r; i++) {
             res = res * total / i;
             total--;
         }
         
         return  (int)res;
     }
 }
```

### Approach 2: Dynamic Programming


#### Complexity analysis
- Time complexity: $O(m * n)$
- Space complexity: $O(m * n)$

```java
 public class Solution {
    
     public int uniquePaths(int m, int n) {        
        Integer[][] map = new Integer[m][n];
        for(int i = 0; i<m;i++){
            map[i][0] = 1;
        }
        for(int j= 0;j<n;j++){
            map[0][j]=1;
        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
}
```

**todo:** Optimization

**todo:** Another approach
https://leetcode.com/problems/unique-paths-ii/discuss/627199/JAVA-Solution-Beats-100-with-DFS-%2B-Memoization


