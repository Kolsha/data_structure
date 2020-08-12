### 7. Reverse Integer

https://leetcode.com/problems/reverse-integer/

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
```
Input: 123
Output: 321
```
Example 2:
```
Input: -123
Output: -321
```
Example 3:
```
Input: 120
Output: 21
```
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: `[−2 ^ 31,  2 ^ 31 − 1]`. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Solution


Approach 1: push-pop mathematicaly
```java
//pop operation:
pop = x % 10;
x /= 10;

//push operation:
temp = rev * 10 + pop;
rev = temp;
```

Complexity Analysis
- Time complexity: O(log(N)) : O(log<sub>10</sub> N &rarr; digit counts)

- Space complexity: O(1)

```java
    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            int pop = x % 10;
            x = x / 10;
            // prevent overflow
            // Integer.MAX_VALUE = 2^31 = 2147483647
            if(res > Integer.MAX_VALUE / 10  || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            
            // Integer.MIN_VALUE = -2^31 = -2147483648
            if(res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE/ 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
```