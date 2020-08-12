### 387. First Unique Character in a String

https://leetcode.com/problems/first-unique-character-in-a-string/

Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.

Examples:
```
s = "leetcode"
return 0.

s = "loveleetcode"
return 2.
```

Note: You may assume the string contains only lowercase English letters.

Solution

Approach: 2 - Pass, HashMap

Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1) because English alphabet contains 26 letters.

```java
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(map.get(ch) == 1) {
                return i;
            }
        }
        return -1;
    }
}
```