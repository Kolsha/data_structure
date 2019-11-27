### 680. Valid Palindrome II

https://leetcode.com/problems/valid-palindrome-ii/

Given a non-empty string `s`, you may delete **at most** one character. Judge whether you can make it a palindrome.

**Example 1:**
```
Input: "aba"
Output: True
```
**Example 2:**
```
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
```
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

**Solution:**
```java
class Solution {
    public boolean validPalindrome(String S) {
        for (int i = 0, j = S.length() - 1; i < j; i++, j--) {
            if (S.charAt(i) != S.charAt(j)) {
                int i1 = i + 1, j1 = j;
                while (i1 < j1 && S.charAt(i1) == S.charAt(j1)) {
                    i1++;
                    j1--;
                }
                int i2 = i, j2 = j - 1;
                while (i2 < j2 && S.charAt(i2) == S.charAt(j2)) {
                    i2++;
                    j2--;
                }
                return i1 >= j1 || i2 >= j2;
            }
        }
        return true;
    }
}
```