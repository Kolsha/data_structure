### 266. Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

Example 1:
```
Input: "code"
Output: false
```
Example 2:
```
Input: "aab"
Output: true
```
Example 3:
```
Input: "carerac"
Output: true
```

Solution
```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (set.contains(temp)) {
                set.remove(temp);
            } else {
                set.add(temp);
            }
        }
        return set.size() == 1 || set.isEmpty();
    }
}
```