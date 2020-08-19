### 20. Valid Parentheses

https://leetcode.com/problems/valid-parentheses/

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
```
Input: "()"
Output: true
```
Example 2:
```
Input: "()[]{}"
Output: true
```
Example 3:
```
Input: "(]"
Output: false
```
Example 4:
```
Input: "([)]"
Output: false
```
Example 5:
```
Input: "{[]}"
Output: true
```

Solution

Approach 1: Stack

Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(n)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch(ch) {
                case '(':
                    stack.push(ch);
                    break;
                
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                
                case '[':
                    stack.push(ch);
                    break;

                case ']':
                    if(stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                
                case '{':
                    stack.push(ch);
                    break;

                case '}':
                    if(stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
```