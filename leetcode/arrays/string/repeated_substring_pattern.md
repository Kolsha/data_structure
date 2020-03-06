### 459. Repeated Substring Pattern

https://leetcode.com/problems/repeated-substring-pattern/

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:
```
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
```
Example 2:
```
Input: "aba"
Output: False
```
Example 3:
```
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
```

Solution

Method 1:
If your string S contains a repeating substring, then this means you can "shift and wrap around" your string some number of times and have it match the original string.

Example: abcabc
```
Shift once: cabcab
Shift twice: bcabca
Shift three times: abcabc
```
Now the string matches the original string, so you know there is a repeated substring.

To avoid doing this weird wraparound and using modulo, you can just create a new string SS that is the original string concatenated with itself, and check if this new string contains the original string (shifted some number of times). However, you don't want it to match with the first half (S) and the second half (S), so you remove the first and last characters.

A similar idea is presented in CTCI problem 1.9

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
```
// todo
Method 2