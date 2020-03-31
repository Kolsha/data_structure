### 159. Longest Substring with At Most Two Distinct Characters

https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:
```
Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
```
Example 2:
```
Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
```

Solution

Time complexity: O(n)

Space complexity: O(1) since additional space is used only for a hashmap with at most 3 elements.

```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int begin = 0, end = 0, len = 0, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (end < s.length()) {
            char temp = s.charAt(end);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            if (map.get(temp) == 1) {
                count++;
            }
            end++;
            while (count > 2) {
                temp = s.charAt(begin);
                map.put(temp, map.get(temp) - 1);
                if (map.get(temp) == 0) {
                    count--;
                }
                begin++;
            }
            // check max length when count <= 2 (At Most Two Distinct Characters)
            len = Math.max(len, end - begin);
        }
        return len;
    }
}
```