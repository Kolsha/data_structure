### 340. Longest Substring with At Most K Distinct Characters

https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:
```
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
```
Example 2:
```
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
```

Solution

Time complexity: O(n)

Space complexity: O(1)

Method 1: Sliding window

In Java:
```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int begin = 0, end = 0, len = 0, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while(end < s.length()) {
            char temp = s.charAt(end);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            if(map.get(temp) == 1) {
                count++;
            }
            end++;
            while(count > k) {
                temp = s.charAt(begin);
                map.put(temp, map.get(temp) - 1);
                if(map.get(temp) == 0) {
                    count--;
                }
                begin++;
            }
            // while count == k
            len = Math.max(len, end - begin);
        }
        return len;
    }
}
```