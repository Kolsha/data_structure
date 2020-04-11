### 202. Happy Number

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 
```
Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```
Solution

Method 1

Time complexity

Space complexity:

```java
class Solution {
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        do {
            fast = getSquareSum(getSquareSum(fast));
            slow = getSquareSum(slow);
        } while (fast != slow);

        return slow == 1;
    }

    int getSquareSum(int n) {
        String val = String.valueOf(n);
        int sum = 0;
        for (int i = 0; i < val.length(); i++) {
            int num = val.charAt(i) - '0';
            sum += num * num;
        }
        return sum;
    }
}
```