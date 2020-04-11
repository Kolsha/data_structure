### 788. Rotated Digits

https://leetcode.com/problems/rotated-digits/

X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
```
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
```
Note:

- N  will be in range [1, 10000].


Solution

Method 1: HashMap - 1 pass

Time complexity: O(n)

Space complexity: O(n)

In Java
```java
class Solution {
    public int rotatedDigits(int N) {
        HashMap<Integer, Integer> rotate = new HashMap<>();
        rotate.put(0, 0);
        rotate.put(1, 1);
        rotate.put(2, 5);
        rotate.put(5, 2);
        rotate.put(6, 9);
        rotate.put(8, 8);
        rotate.put(9, 6);
        
        int count = 0;
        for(int i = 1; i <= N; i++) {
            if(isGoodNum(i, rotate)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isGoodNum(int num, HashMap<Integer, Integer> map) {
        int rotatedNum = -1;
        String numStr = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numStr.length(); i++) {
            int numKey = numStr.charAt(i) - '0';
            if(map.containsKey(numKey)) {
                sb.append(map.get(numKey));
            } else {
                return false;
            }
        }
        return !numStr.equals(sb.toString());
    }
}
```