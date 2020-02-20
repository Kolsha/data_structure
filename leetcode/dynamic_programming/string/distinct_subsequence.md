### 115. Distinct Subsequences
https://leetcode.com/problems/distinct-subsequences/

Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:
```
Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
```
Example 2:
```
Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
```

Solution:

ref: https://www.youtube.com/watch?v=mPqqXh8XvWY

```java
class Solution {
    public int numDistinct(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        // dp[i][j] represents the number of distinct subsequences for s[0, i-1] and
        // t[0, t-1];
        // We first keep in mind that s is the longer string [0, i-1], t is the shorter
        // substring [0, j-1].
        // We can assume t is fixed, and s is increasing in character one by one (this
        // is the key point).

        // For example:
        // t : ab--> ab --> ab --> ab
        // s: a --> ac --> acb --> acbb

        int[][] dp = new int[lenS + 1][lenT + 1];

        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                if (i == 0 && j == 0) {
                    // empty string contains the empty string 1 time
                    dp[i][j] = 1;
                } else if (i == 0) {
                    // non-empty string is not empty string's substring
                    dp[i][j] = 0;
                } else if (j == 0) {
                    // T(j) = empty string which is any string's substring
                    dp[i][j] = 1;
                } else if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    // if the current character in S doesn't equal to current character T,
                    // then we have the same number of distinct subsequences as we had without the
                    // new character.
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // todo still need to figure this out
                    // For example:
                    // t : ab--> ab --> ab --> ab
                    // s: a --> ac --> acb -> acbb
                    // When two pointers pointing to the same character
                    // if we take these two characters simultaneously, we should have dp[i-1][j-1]
                    // subsequences
                    // otherwise if we overlook current i (moving back for one step) and keeping the
                    // current j we have another dp[i -1][j]
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }

            }
        }
        return dp[lenS][lenT];
    }
}
```