### 633. Sum of Square Numbers

https://leetcode.com/problems/sum-of-square-numbers/

Given a non-negative integer `c`, your task is to decide whether there're two integers a and b such that a<sup>2</sup> + b<sup>2</sup> = c.

**Example 1:**
```
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
```

**Example 2:**
```
Input: 3
Output: False
```

**Solution:**
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        HashSet<Integer> set = new HashSet<>();
        int maxValue = (int) Math.sqrt(c);
        for (int i = 0; i <= maxValue; i++) {
            set.add(i * i);
            int another = c - i * i;
            if (set.contains(another)) {
                return true;
            }
        }
        return false;
    }
}
```