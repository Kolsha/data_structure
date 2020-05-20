### 670. Maximum Swap

https://leetcode.com/problems/maximum-swap/

Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
```
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
```
Example 2:
```
Input: 9973
Output: 9973
Explanation: No swap.
```
Note:
 - The given number is in the range [0, 10<sup>8</sup>]


Solution

```java
class Solution {
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        
        int[] bucket = new int[10];
        
        for(int i = 0; i < charArray.length; i++) {
            char digitChar = charArray[i];
            bucket[digitChar - '0'] = i;
        }
        
        for(int i = 0; i < charArray.length; i++) {
            for(int k = 9; k > charArray[i] - '0'; k--) {
                // find where can be swapped
                if(bucket[k] > i) {
                    // swap index bucket[k] and i
                    char tmp = charArray[i];
                    charArray[i] = charArray[bucket[k]];
                    charArray[bucket[k]] = tmp;
                    return Integer.valueOf(String.valueOf(charArray));
                }
            }
        }
        return num;
    }
}
```

// optimized approach
https://leetcode.com/problems/maximum-swap/discuss/107068/Java-simple-solution-O(n)-time
