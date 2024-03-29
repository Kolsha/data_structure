### [91. Decode Ways](https://leetcode.com/problems/decode-ways/)

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
```
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
```
Example 2:
```
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

Solution
```java
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];

        // empty string, only 1 way decoding
        dp[0] = 1;
        dp[1] = s.charAt(0) - '0' > 0 ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            if (oneDigit > 0 && oneDigit < 10) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits > 9 && twoDigits < 27) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}
```