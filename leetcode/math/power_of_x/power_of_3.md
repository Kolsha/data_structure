### 326. Power of Three

https://leetcode.com/problems/power-of-three/


Given an integer, write a function to determine if it is a power of three.

**Example 1:**
```
Input: 27
Output: true
```
**Example 2:**
```
Input: 0
Output: false
```

**Example 3:**
```
Input: 9
Output: true
```

**Solution:**
```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }
}
```