### 461. Hamming Distance

https://leetcode.com/problems/hamming-distance/

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 2<sup>31</sup>.

Example:

Input: x = 1, y = 4

Output: 2
```
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
```

Solution

Approach 1: Build-In function + XOR

Complexity analysis:
- Time complexity: O(1) -> integer bit size (32 bits)
- Space complexity: O(1)

```java
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
```

Approach 2: 
- Time complexity: O(1)
- Space complexity: O(1)

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int dist = 0;
        while(xor != 0) {
            if((xor & 1) == 1) {
                dist++;
            }
            xor = xor >> 1;
        }
        return dist;
    }
}
```