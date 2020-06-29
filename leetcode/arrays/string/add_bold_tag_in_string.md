### 616. Add Bold Tag in String

https://leetcode.com/problems/add-bold-tag-in-string/

Given a string `s` and a list of strings `dict`, you need to add a closed pair of bold tag `<b>` and `</b>` to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
```
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
``` 

Example 2:
```
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
``` 

Constraints:

- The given dict won't contain duplicates, and its length won't exceed 100.
- All the strings in input have length in range [1, 1000].

Solution

Approach 1: Boolean Array

```java
class Solution {
    public String addBoldTag(String s, String[] dict) {
        
        boolean[] match = new boolean[s.length()];
        for(int i = 0, end = 0; i < s.length(); i++) {
            for(String word:dict) {
                if(s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
                match[i] = end > i;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(!match[i]) {
                res.append(s.charAt(i));
                continue;
            }
            int lastBoldIndex = i;
            while(lastBoldIndex < s.length() && match[lastBoldIndex]) {
                lastBoldIndex++;
            }
            res.append("<b>"+s.substring(i, lastBoldIndex)+"</b>");
            i = lastBoldIndex - 1;
        }
        return res.toString();
    }
}
```