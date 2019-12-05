### 1256. Encode Number
https://leetcode.com/problems/encode-number/

Given a non-negative integer `num`, Return its encoding string.

The encoding is done by converting the integer to a string using a secret function that you should deduce from the following table:

![](https://assets.leetcode.com/uploads/2019/06/21/encode_number.png)

Example 1:
```
Input: num = 23
Output: "1000"
```
Example 2:
```
Input: num = 107
Output: "101100"
```

Constraints:

0 <= num <= 10^9

**Solution:**
<br/>from:
https://leetcode.com/problems/encode-number/discuss/430488/JavaC%2B%2BPython-Binary-of-n-%2B-1

The following sequence can be built up form the ealier result.
So I search index of the prefix part
For example:
```
f(5) = "10"
f(6) = "11"
```
The prefix are both `f(2) = "1"`

so we found that `f(n)` has `f((n - 1) / 2)` as prefix.
```java
class Solution {
    public String encode(int n) {
        return n > 0 ? encode((n - 1) / 2) + "10".charAt(n % 2) : "";
    }
}
```