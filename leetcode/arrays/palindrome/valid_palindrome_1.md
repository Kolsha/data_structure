### [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

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

### Solution:

#### Approach 1: Two - Pointers
##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

###### Java
```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i < j) {
            if(!Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            } else {
                if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))) {
                    return false;
                }
                ++i;
                --j;
            }
        }
        return true;

    }
}

```

###### Python
```python
class Solution:
    # isalnum() 如果 string 至少有一个字符并且所有字符都是字母或数字则返回 True,否则返回 False
    def isPalindrome(self, s: str) -> bool:
        l, r = 0, len(s)-1
        while l < r:
            while l < r and not s[l].isalnum():
                l += 1
            while l <r and not s[r].isalnum():
                r -= 1
            if s[l].lower() != s[r].lower():
                return False
            l +=1; r -= 1
        return True
```