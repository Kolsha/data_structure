### 32. Longest Valid Parentheses
https://leetcode.com/problems/longest-valid-parentheses/

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
```
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
```
Example 2:
```
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
```

Solution
```java
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // dp[i]: value of the longest valid parentheses at index i
        // if i == '(', then dp[i] == 0
        // if i == ')'
        // 1. if index i - 1 == '(', then dp[i] = dp[i-2] + 2
        // 2. if index i - 1 == ')',
        // ex: ( ( ( ) )
        //     0 1 2 3 4
        // in this example, when i = 4, the char is ')', index 3 is also ')',
        // we need to check if index 1 (4 - dp[3] - 1 => i - dp[i-1] - 1 => We make
        // variable 'x' to hold the value here) is:
        // '(': dp[i] = dp[i-1] + 2 + dp[x-1];
        // ')': dp[i] = 0;
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == ')') {
                char lastChar = s.charAt(i - 1);
                if (lastChar == '(') {
                    dp[i] = (i >= 2) ? dp[i - 2] + 2 : 2;
                    max = Math.max(max, dp[i]);
                }

                int x = i - dp[i - 1] - 1;
                if (lastChar == ')' && x >= 0 && s.charAt(x) == '(') {
                    dp[i] = dp[i - 1] + 2 + ((x - 1 >= 0) ? dp[x - 1] : 0);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
```
