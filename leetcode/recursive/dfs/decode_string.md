### 394. Decode String

https://leetcode.com/problems/decode-string/

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

Solution

Method 1: DFS

Time complexity: O(n * max_count) -> One pass and we need to add repeated strings to StringBuilder

Space complexity: O(n) -> depends on how many '[' we have in the String

```java
class Solution {
    int pos = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int repeatCount = 0;
        
        for(int i = pos; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                repeatCount = repeatCount * 10 + (ch - '0');
            } else if(ch == '[') {
                pos = i + 1;
                String next = decodeString(s);
                while(repeatCount!=0) {
                    sb.append(next);
                    repeatCount--;
                }
                i = pos;
            } else if(ch == ']') {
                pos = i;
                return sb.toString();
            } else {
                // if it's alphabet
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
```