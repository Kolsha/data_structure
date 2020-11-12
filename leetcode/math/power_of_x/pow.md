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
##### Approach 1: Recursive
##### Complexity analysis:
- Time complexity: O(logn)
- Space complexity: O(logn)

```java
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

##### Approach 2: Iterative
##### Complexity analysis
- Time complexity: O(logn)
- Space complexity: O(1)

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
};
```