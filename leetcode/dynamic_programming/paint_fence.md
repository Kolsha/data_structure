### 276. Paint Fence
https://leetcode.com/problems/paint-fence/

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:
```
Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
   ```

Solution:
   [Very clear explaination](https://leetcode.com/problems/paint-fence/discuss/178010/The-only-solution-you-need-to-read)

- Define subproblems<br/>
Let D<sub>n</sub> be the number of ways to paint the fence
- Find the recurrence<br/>
&rarr; D<sub>n</sub> = (k - 1) * ( D<sub>n-2</sub> + D<sub>n-1</sub>)


Solve the base cases
  1. D<sub>1</sub> = k, D<sub>2</sub> = k * k
```java
   class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }

        if (n == 2) {
            return k * k;
        }

        int first = k, second = k * k, result = 0;
        for (int i = 3; i < n + 1; i++) {
            result = (first + second) * (k - 1);
            first = second;
            second = result;
        }
        return result;
    }
}
```