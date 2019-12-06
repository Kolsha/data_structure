### 50. Pow(x, n)

https://leetcode.com/problems/powx-n/

Implement pow(x, n), which calculates x raised to the power n (x<sup>n</sup>).

**Example 1:**

Input: 2.00000, 10

Output: 1024.00000

**Example 2:**

Input: 2.10000, 3

Output: 9.26100

**Example 3:**

Input: 2.00000, -2
Output: 0.25000

Explanation: 2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25

Note:

- -100.0 < x < 100.0
- n is a 32-bit signed integer, within the range [−2<sup>31</sup>, 2<sup>31</sup> − 1]


**Solution:**
```java
// todo we still need non-recursive solution here

// recursive solution
// time complexity O(logN)
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }
    
        if(n == 1) {
            return x;
        }
    
        if(n < 0) {
            return 1/x * myPow(1/x, -(n+1));
        }
        
        double t = myPow(x, n/2);
        return t * t * myPow(x, n%2);
    }
}
```