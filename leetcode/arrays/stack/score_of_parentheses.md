### 856. Score of Parentheses
https://leetcode.com/problems/score-of-parentheses/


Given a balanced parentheses string `S`, compute the score of the string based on the following rule:

`()` has score 1
`AB` has score `A + B`, where A and B are balanced parentheses strings.
`(A)` has score `2 * A`, where A is a balanced parentheses string.

**Example 1:**
```
Input: "()"
Output: 1
```
**Example 2:**
```
Input: "(())"
Output: 2
```
**Example 3:**
```
Input: "()()"
Output: 2
```
**Example 4:**
```
Input: "(()(()))"
Output: 6
``` 

Note:

1. S is a balanced parentheses string, containing only ( and ).
2. 2 <= S.length <= 50


**Solution:**

from: https://leetcode.com/problems/score-of-parentheses/discuss/141777/C%2B%2BJavaPython-O(1)-Space

cur record the score at the current layer level.

If we meet '(',
we push the current score to stack,
enter the next inner layer level,
and reset cur = 0.

If we meet ')',
the cur score will be doubled and will be at least 1.
We exit the current layer level,
and set cur = stack.pop() + cur

Complexity: O(N) time and O(N) space
```java
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur * 2, 1);
            }
        }
        return cur;
    }
}
```