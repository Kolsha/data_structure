### 125. Valid Palindrome

https://leetcode.com/problems/valid-palindrome/

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

**Example 1:**
```
Input: "A man, a plan, a canal: Panama"
Output: true
```
**Example 2:**
```
Input: "race a car"
Output: false
```
**Solution:**
```java
class Solution {
    public boolean isPalindrome(String s) {
        // rebuild input string
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(temp >= 'a' && temp <= 'z' || temp >= '0' && temp <= '9') {
                sb.append(temp);
            }
        }
        // update input string
        s = sb.toString();
        System.out.println(s);
        if(s.length() == 0) {
            return true;
        }else if(s.length() == 1) {
            return true;
        } 

        int i = -1, j = s.length();
        while(++i < --j) {
            if(s.charAt(i)!=s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

```