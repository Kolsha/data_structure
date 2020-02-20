### 97. Interleaving String
https://leetcode.com/problems/interleaving-string/

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:
```
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
```
Example 2:
```
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
```

Solution
Method 1: Brute force - run through each interleaving sample => Very poor performance

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, 0, s2, 0, "", s3);
        
    }
    
    private boolean isInterleave(String s1, int i, String s2, int j, String res, String s3) {
        if(s3.equals(res) && i == s1.length() && j == s2.length()) {
            return true;
        }
        
        boolean ans = false;
        if(i < s1.length()) {
            ans = ans | isInterleave(s1, i+1, s2, j, res+s1.charAt(i), s3);
        }
        
        if(j < s2.length()) {
            ans = ans | isInterleave(s1, i, s2, j+1, res+s2.charAt(j), s3);
        }
        return ans;
    }
}
```

Method 2: Dynamic programming

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int lenS1 = s1.length();
        int lenS2 = s2.length();
        if (s3.length() != (lenS1 + lenS2)) {
            return false;
        }
        boolean[][] dp = new boolean[lenS1 + 1][lenS2 + 1];

        for (int i = 0; i <= lenS1; i++) {
            for (int j = 0; j <= lenS2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)
                            || dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[lenS1][lenS2];
    }
}
```