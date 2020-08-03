### 242. Valid Anagram

https://leetcode.com/problems/valid-anagram/

Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
```
Input: s = "anagram", t = "nagaram"
Output: true
```
Example 2:
```
Input: s = "rat", t = "car"
Output: false
```
Note:
- You may assume the string contains only lowercase alphabets.

Follow up:
- What if the inputs contain unicode characters? How would you adapt your solution to such case?

Solution

Approach 1: Three pass alphabet array

Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        Arrays.fill(map, 0);
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map[ch - 'a'] += 1; 
        }
        
        for(int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map[ch - 'a'] -= 1;
        }
        
        for(int i = 0; i < 26; i++) {
            if(map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```