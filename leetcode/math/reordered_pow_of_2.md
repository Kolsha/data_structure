### 869. Reordered Power of 2

https://leetcode.com/problems/reordered-power-of-2/


Starting with a positive integer `N`, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return `true` if and only if we can do this in a way such that the resulting number is a power of 2.

**Example 1:**
```
Input: 1
Output: true
```
**Example 2:**
```
Input: 10
Output: false
```
**Example 3:**
```
Input: 16
Output: true
```
**Example 4:**
```
Input: 24
Output: false
```
**Example 5:**
```
Input: 46
Output: true
``` 

Note:

1 <= N <= 10^9

**Solution:**
```java
class Solution {
    public boolean reorderedPowerOf2(int N) {
        char[] inputCharArray = String.valueOf(N).toCharArray();
        Arrays.sort(inputCharArray);
        String sortedInputStr = String.valueOf(inputCharArray);
        // 因为 1 <= N <= 10^9
        // 10^9 = (1000)^3
        // 10^9 < (1024)^3
        // 10^9 < (2^10)^3
        // 10^9 < 2^30 => 
        // 所以 2^0 <= N < 2^30
        for (int i = 0; i < 30; i++) {
            int num = 1 << i;
            char[] powerTwoChars = String.valueOf(num).toCharArray();
            Arrays.sort(powerTwoChars);
            if (sortedInputStr.equals(String.valueOf(powerTwoChars))) {
                return true;
            }
        }
        return false;
    }
}
```