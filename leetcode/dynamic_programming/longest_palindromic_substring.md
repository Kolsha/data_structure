### 5. Longest Palindromic Substring
https://leetcode.com/problems/longest-palindromic-substring/

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```
Example 2:
```
input: "cbbd"
Output: "bb"
```
Solution
```java
class Solution {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i; j < s.length(); j++) {
                // why do we need j - i < 3 ->
                // if window is less than or equal to 3, it's palindrome
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    int strLen = j - i + 1;
                    if (res.length() < strLen) {
                        res = s.substring(i, j + 1);
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return res;
    }
}
```