### 224. Basic Calculator

https://leetcode.com/problems/basic-calculator/

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open `(` and closing parentheses `)`, the plus `+` or minus sign `-`, non-negative integers and empty spaces .

Example 1:
```
Input: "1 + 1"
Output: 2
```

Example 2:
```
Input: " 2-1 + 2 "
Output: 3
```
Example 3:
```
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
```

Note:
- You may assume that the given expression is always valid.
- Do not use the eval built-in library function.
**Solution:**
Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
Only 5 possible input we need to pay attention:

- `digit`: it should be one digit from the current number
- `'+'`: number is over, we can add the previous number and start a new number
- `'-'`: same as above
- `'('`: push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
- `')'`: pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.

Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.

```java
class Solution {
    public int calculate(String s) {
        int sign = 1;
        int result = 0;
        int num = 0;
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            // handle while curr is digit
            if (Character.isDigit(temp)) {
                int curr = temp - '0';
                num = 10 * num + curr;
            }
            if (temp == '+') {
                result = result + sign * num;
                sign = 1;
                num = 0;

            }
            if (temp == '-') {
                result = result + sign * num;
                sign = -1;
                num = 0;
            }
            if (temp == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }
            if (temp == ')') {
                result = result + sign * num;
                num = 0;
                sign = 1;
                result = stack.pop() * result;
                result = stack.pop() + result;
            }
        }

        if (num != 0) {
            result = result + sign * num;
        }
        return result;
    }
}
```