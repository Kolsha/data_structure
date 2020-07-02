### 227. Basic Calculator II

https://leetcode.com/problems/basic-calculator-ii/

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
```
Input: "3+2*2"
Output: 7
```
Example 2:
```
Input: " 3/2 "
Output: 1
```
Example 3:
```
Input: " 3+5 / 2 "
Output: 5
```
Note:

- You may assume that the given expression is always valid.
- Do not use the eval built-in library function.

Solution

Approach 1: Stack

```java
class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        char sign = '+';
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(Character.isDigit(cur)) {
                temp = temp * 10 + cur - '0';
            }
            // calcute temp and push it to stack
            // cur != ' '  -> " 3/2 "
            if(!Character.isDigit(cur) && cur!= ' ' || i == s.length()-1) {
                switch (sign) {
                    case '+':
                        stack.push(temp);
                        break;

                    case '-':
                        stack.push(-temp);
                        break;

                    case '*':
                        stack.push(stack.pop() * temp);
                        break;

                    case '/':
                        stack.push(stack.pop() / temp);
                        break;

                    default:
                        break;
                }
                sign = cur;
                temp = 0;
            }
        }
        
        int res = 0;
        for(int num : stack) {
            res += num;
        }
        return res;
    }
}
```



#facebook