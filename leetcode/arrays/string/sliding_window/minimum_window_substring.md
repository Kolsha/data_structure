### 76. Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
```
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
```

Note:

- If there is no such window in S that covers all characters in T, return the empty string "".
- If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

Solution

Method 1: Sliding window algorithm

```java
class Solution {
    public String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) {
            return res;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int begin = 0, end = 0, len = Integer.MAX_VALUE, count = map.size();
        int head = 0;

        while (end < s.length()) {
            char temp = s.charAt(end);
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) - 1);
                if (map.get(temp) == 0) {
                    count--;
                }
            }
            end++;
            while (count == 0) {
                temp = s.charAt(begin);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0) {
                        count++;
                    }
                }

                if (end - begin < len) {
                    len = end - begin;
                    head = begin;
                }
                begin++;
            }
        }

        return (len == Integer.MAX_VALUE) ? "" : s.substring(head, head + len);
    }
}
```