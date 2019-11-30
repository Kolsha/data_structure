### 1017. Convert to Base -2

https://leetcode.com/problems/convert-to-base-2/

Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".

 

Example 1:
```
Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
```
Example 2:
```
Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
```
Example 3:
```
Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4
```
**Solution:**
<br/>from:<br/>
https://leetcode.com/problems/convert-to-base-2/discuss/265507/JavaC%2B%2BPython-2-lines-Exactly-Same-as-Base-2

```java
    public String baseNeg2(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);
            N = -(N >> 1);
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }
``` 

Note:

0 <= N <= 10^9