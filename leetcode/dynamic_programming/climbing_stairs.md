### [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

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
### Solution:

#### Approach 1: Dynamic programming

- Define subproblems<br/>
Let D<sub>n</sub> be the number of ways to climb the stairs.
- Find the recurrence<br/>
Since we can climb 1 stair or 2 stairs at a time, in the final phase, we will only have 1 or 2 stairs left.<br/>
 或者可以这样想：n 个阶梯的走法，是由第一步为一阶的走法(也就是走剩下的 n - 1 阶的走法) + 第一步为二阶的走法所组成( 也就是走 n - 2 阶的走法)。<br/>
&rarr; D<sub>n</sub> = D<sub>n - 1</sub> + D<sub>n - 2</sub><br/>

- Solve the base cases
  1. D<sub>1</sub> = 1, D<sub>2</sub> = 2

Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

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

#### Recursive approach
```java
class Solution {
    private static HashMap<Integer, Integer> map = new HashMap<>();
    static {
        map.put(1, 1);
        map.put(2, 2);
    }
    public int climbStairs(int n) {
        if(n < 3) {
            return map.get(n);
        }
        
        if(map.containsKey(n)) {
            return map.get(n);
        }

        int res = climbStairs(n - 1) + climbStairs(n - 2);
        map.put(n, res);
        return res;
    }
}
```

#### Other approaches:
```python
# Top down - TLE
def climbStairs1(self, n):
    if n == 1:
        return 1
    if n == 2:
        return 2
    return self.climbStairs(n-1)+self.climbStairs(n-2)
 
# Bottom up, O(n) space
def climbStairs2(self, n):
    if n == 1:
        return 1
    res = [0 for i in xrange(n)]
    res[0], res[1] = 1, 2
    for i in xrange(2, n):
        res[i] = res[i-1] + res[i-2]
    return res[-1]

# Bottom up, constant space
def climbStairs3(self, n):
    if n == 1:
        return 1
    a, b = 1, 2
    for i in xrange(2, n):
        tmp = b
        b = a+b
        a = tmp
    return b
    
# Top down + memorization (list)
def climbStairs4(self, n):
    if n == 1:
        return 1
    dic = [-1 for i in xrange(n)]
    dic[0], dic[1] = 1, 2
    return self.helper(n-1, dic)
    
def helper(self, n, dic):
    if dic[n] < 0:
        dic[n] = self.helper(n-1, dic)+self.helper(n-2, dic)
    return dic[n]
    
# Top down + memorization (dictionary)  
def __init__(self):
    self.dic = {1:1, 2:2}
    
def climbStairs(self, n):
    if n not in self.dic:
        self.dic[n] = self.climbStairs(n-1) + self.climbStairs(n-2)
    return self.dic[n]
```
