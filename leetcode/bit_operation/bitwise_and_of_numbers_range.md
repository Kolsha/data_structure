### 201. Bitwise AND of Numbers Range

https://leetcode.com/problems/bitwise-and-of-numbers-range/

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:
```
Input: [5,7]
Output: 4
```
Example 2:
```
Input: [0,1]
Output: 0
```

[Solution](https://leetcode.com/articles/bitwise-and-of-numbers-range/)

Method 1: Bit shift

Time complexity: O(1)

Space complexity: O(1)
```java
class Solution {
  public int rangeBitwiseAnd(int m, int n) {
    int shift = 0;
    // find the common 1-bits
    while (m < n) {
      m >>= 1;
      n >>= 1;
      ++shift;
    }
    return m << shift;
  }
}
```